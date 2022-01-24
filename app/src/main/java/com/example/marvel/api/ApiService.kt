package com.example.marvel.api

import com.example.marvel.db.models.ErrorResponse
import com.example.marvel.db.models.HeroResponse
import com.example.marvel.db.models.NetworkResponse
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("/v1/public/characters?")
   suspend fun getCharacterAsync(
        @Query("nameStartsWith") name: String,
        @Query("ts") ts: String,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String
    ): NetworkResponse<HeroResponse, ErrorResponse>
}