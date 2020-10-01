package com.example.marvelcomicsviewer.shared.characters

import com.example.marvelcomicsviewer.shared.arch.DataStore
import com.example.marvelcomicsviewer.shared.arch.httpClient
import com.example.marvelcomicsviewer.shared.characters.api.CharacterNetworkDataStore
import kotlinx.coroutines.flow.Flow

class CharacterRepository(
    private val networkDataStore: DataStore<Int, Character> = CharacterNetworkDataStore(httpClient)
) {
    suspend fun fetchAllCharacters(): Flow<List<Character>> {
        return networkDataStore.fetchAll()
    }

    suspend fun fetchCharacter(id: Int): Flow<Character?> {
        return networkDataStore.fetch(id)
    }
}