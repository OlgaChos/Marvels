package com.example.marvel.api

import com.example.marvel.db.models.ErrorResponse
import com.example.marvel.db.models.HeroResponse
import com.example.marvel.db.models.NetworkResponse
import com.example.marvel.utils.PRIVATE_KAY
import com.example.marvel.utils.PUBLIC_KAY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class ApiRepository {

    private val apiService = RetrofitClient.getClient().create(ApiService::class.java)

    fun getHero(name: String): NetworkResponse<HeroResponse, ErrorResponse> {
        return runBlocking(Dispatchers.IO) {
            val ts = (System.currentTimeMillis() / 1000).toString()
            val hash = md5(ts + PRIVATE_KAY + PUBLIC_KAY)
            apiService.getCharacterAsync(name, ts, PUBLIC_KAY, hash)
        }
    }


    private fun md5(s: String): String {
        try {
            val digest: MessageDigest = MessageDigest
                .getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest: ByteArray = digest.digest()
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}