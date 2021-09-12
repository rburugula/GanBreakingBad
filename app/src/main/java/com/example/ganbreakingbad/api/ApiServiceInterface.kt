package com.example.ganbreakingbad.api

import com.example.ganbreakingbad.model.Character
import retrofit2.http.GET

interface ApiServiceInterface {
    @GET("api/characters")
    suspend fun getCharacters(): ArrayList<Character>
}