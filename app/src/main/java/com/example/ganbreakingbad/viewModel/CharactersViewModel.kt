package com.example.ganbreakingbad.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ganbreakingbad.data.DataRepository
import com.example.ganbreakingbad.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {
    private val _charactersLiveData = MutableLiveData<Result<ArrayList<Character>>>()
    val charactersLiveData: LiveData<Result<ArrayList<Character>>> = _charactersLiveData

    fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _charactersLiveData.postValue(dataRepository.fetchCharacters())
        }
    }
}