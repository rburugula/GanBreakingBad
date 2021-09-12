package com.example.ganbreakingbad.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.ganbreakingbad.MainCoroutineTestRule
import com.example.ganbreakingbad.data.DataRepository
import com.example.ganbreakingbad.model.Character
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.util.ArrayList

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = MainCoroutineTestRule()

    private val dataRepository: DataRepository = mockk(relaxed = true)
    private var observer: Observer<Result<List<Character>>> = mockk(relaxed = true)

    @Test
    fun `WHEN getCharacters is successful THEN call once the corresponding repository and the success data`() {
        coEvery { dataRepository.fetchCharacters() } returns Result.success(buildFakeCharacters())

        val viewModel = buildMainActivityViewModel()
        viewModel.charactersLiveData.observeForever(observer)

        runBlockingTest {
            viewModel.fetchCharacters()

            coVerify(exactly = 1) { dataRepository.fetchCharacters() }

            val slot = slot<Result<ArrayList<Character>>>()
            verify { observer.onChanged(capture(slot)) }
            Assert.assertTrue(slot.captured.isSuccess)

            viewModel.charactersLiveData.removeObserver(observer)
        }
    }

    private fun buildFakeCharacters() = arrayListOf(
        Character(
            name = "myName",
            img = "image",
            occupation = arrayListOf(),
            status = "status",
            nickname = "nickName",
            appearance = arrayListOf()
        )
    )

    private fun buildMainActivityViewModel() = CharactersViewModel(
        dataRepository
    )
}