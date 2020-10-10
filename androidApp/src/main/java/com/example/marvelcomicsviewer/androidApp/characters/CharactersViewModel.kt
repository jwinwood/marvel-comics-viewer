package com.example.marvelcomicsviewer.androidApp.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcomicsviewer.shared.characters.domain.Character
import com.example.marvelcomicsviewer.shared.characters.domain.FetchAllCharactersUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

data class CharacterViewState(
    val loading: Boolean = false,
    val data: List<Character> = emptyList()
)
private val initialViewState = CharacterViewState()

@FlowPreview
@ExperimentalCoroutinesApi
class CharactersViewModel(
    private val useCase: FetchAllCharactersUseCase
) : ViewModel() {
    private val _viewState: MutableStateFlow<CharacterViewState> = MutableStateFlow(initialViewState)
    val viewState: StateFlow<CharacterViewState> = _viewState

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        _viewState.value = CharacterViewState(loading = true)
        viewModelScope.launch(Dispatchers.IO) {
            useCase.invoke(null)
                .collect { characters ->
                    withContext(Dispatchers.Main) {
                        _viewState.value = (
                            CharacterViewState(loading = false, data = characters)
                        )
                    }
                }
        }
    }
}