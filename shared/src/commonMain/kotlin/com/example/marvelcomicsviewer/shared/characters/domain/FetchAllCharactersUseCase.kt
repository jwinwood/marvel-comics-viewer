package com.example.marvelcomicsviewer.shared.characters.domain

import com.example.marvelcomicsviewer.shared.arch.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FetchAllCharactersUseCase(
    dispatcher: CoroutineDispatcher,
    private val repository: CharacterRepository
) : BaseUseCase<Unit, List<Character>>(dispatcher) {


    override suspend fun invoke(param: Unit?): Flow<List<Character>> =
        withContext(dispatcher) {
            repository.fetchCharacters()
        }
}