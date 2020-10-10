package com.example.marvelcomicsviewer.shared.characters.domain

import com.example.marvelcomicsviewer.shared.arch.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

class FetchACharacterUseCase(
    dispatcher: CoroutineDispatcher,
    private val repository: CharacterRepository
) : BaseUseCase<Int, Character?>(dispatcher) {

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun invoke(param: Int?): Flow<Character?> =
        withContext(dispatcher) {
            repository.fetchCharacters()
                .flatMapConcat { flowOf(it.firstOrNull { character -> character.id == param }) }
        }
}