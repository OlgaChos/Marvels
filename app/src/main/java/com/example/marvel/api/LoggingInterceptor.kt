package com.example.marvel.api

import android.util.Log
import okhttp3.*
import okio.Buffer


class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        val t1 = System.nanoTime()
        Log.d(
            "OkHttp",
            java.lang.String.format(
                "--> Sending request %s on %s%n%s",
                request.url(),
                chain.connection(),
                request.headers()
            )
        )

        val requestBuffer = Buffer()
        request.body()?.writeTo(requestBuffer)
        Log.d("OkHttp", requestBuffer.readUtf8())

        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        Log.d(
            "OkHttp",
            String.format(
                "<-- Received response for %s in %.1fms%n%s",
                response.request().url(),
                (t2 - t1) / 1e6,
                response.headers()
            )
        )

        val contentType: MediaType? = response.body()!!.contentType()
        val content = response.body()!!.string()
        Log.d("OkHttp", content)

        val wrappedBody = ResponseBody.create(contentType, content)
        return response.newBuilder().body(wrappedBody).build()
    }
}