package com.example.marvelcomicsviewer.androidApp.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcomicsviewer.shared.characters.Character
import com.example.marvelcomicsviewer.shared.characters.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class CharacterViewState(
    val loading: Boolean = false,
    val data: List<Character> = emptyList()
)

class CharactersViewModel : ViewModel() {
    private val repository = CharacterRepository()

    private val _viewState: MutableLiveData<CharacterViewState> = MutableLiveData()
    val viewState: LiveData<CharacterViewState> = _viewState

    fun fetchCharacters() {
        _viewState.value = CharacterViewState(loading = true)
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchAllCharacters()
                .collect { characters ->
                    withContext(Dispatchers.Main) {
                        _viewState.value = (CharacterViewState(loading = false, data = characters))
                    }
                }
        }
    }
}