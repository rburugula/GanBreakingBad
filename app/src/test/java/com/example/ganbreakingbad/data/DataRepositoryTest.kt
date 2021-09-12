package com.example.ganbreakingbad.data

import com.example.ganbreakingbad.MainCoroutineTestRule
import com.example.ganbreakingbad.api.ApiServiceInterface
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DataRepositoryTest {

    @get:Rule
    val coroutinesTestRule = MainCoroutineTestRule()

    private val apiServiceInterface: ApiServiceInterface = mockk()

    @Test
    fun `WHEN getCharacters is called THEN call once the corresponding service`() {
        val repository = buildRepository()

        runBlockingTest {
            repository.fetchCharacters()

            coVerify(exactly = 1) { apiServiceInterface.fetchCharacters() }
        }
    }

    private fun buildRepository() = DataRepository(
        apiServiceInterface = apiServiceInterface
    )
}