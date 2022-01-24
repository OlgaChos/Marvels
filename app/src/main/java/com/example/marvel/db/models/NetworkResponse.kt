package com.example.marvel.db.models

import java.io.IOException

sealed class NetworkResponse<out S : Any, out E : Any> {

    /**
     * Success response with body
     */
    data class Success<S : Any>(val value: S) : NetworkResponse<S, Nothing>()

    /**
     * Failure response with body
     */

    data class ApiError<E : Any>(val body: E, val code: Int) : NetworkResponse<Nothing, E>()

    /**
     * Network error
     */

    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()

    /**
     * For example, json parsing error
     */
    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()


}
