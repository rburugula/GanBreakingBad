package com.example.ganbreakingbad.data

import com.example.ganbreakingbad.api.ApiServiceInterface
import com.example.ganbreakingbad.model.Character
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val apiServiceInterface: ApiServiceInterface
) {
    suspend fun fetchCharacters(): Result<ArrayList<Character>> = kotlin.runCatching {
        apiServiceInterface.fetchCharacters()
    }
}