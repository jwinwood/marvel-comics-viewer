package com.example.marvelcomicsviewer.androidApp.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcomicsviewer.shared.characters.Character
import com.example.marvelcomicsviewer.shared.characters.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class CharacterViewState(
    val loading: Boolean = false,
    val data: List<Character> = emptyList()
)
private val initialViewState = CharacterViewState()

@ExperimentalCoroutinesApi
class CharactersViewModel(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _viewState: MutableStateFlow<CharacterViewState> = MutableStateFlow(initialViewState)
    val viewState: StateFlow<CharacterViewState> = _viewState

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
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