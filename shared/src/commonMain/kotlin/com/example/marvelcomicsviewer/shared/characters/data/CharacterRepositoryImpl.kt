package com.example.marvelcomicsviewer.shared.characters.data

import com.example.marvelcomicsviewer.shared.arch.DataStore
import com.example.marvelcomicsviewer.shared.arch.httpClient
import com.example.marvelcomicsviewer.shared.characters.data.api.CharacterDataWrapper
import com.example.marvelcomicsviewer.shared.characters.domain.Character
import com.example.marvelcomicsviewer.shared.characters.data.api.CharacterNetworkDataStore
import com.example.marvelcomicsviewer.shared.characters.data.api.Image
import com.example.marvelcomicsviewer.shared.characters.domain.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl(
    private val networkDataStore: DataStore<CharacterDataWrapper> = CharacterNetworkDataStore(httpClient)
) : CharacterRepository {

    override suspend fun fetchCharacters(): Flow<List<Character>> =
        networkDataStore.fetch()
            .map { it.data.results }
            .map { transformToCharacterList(it) }

    private fun transformToCharacterList(characters: List<com.example.marvelcomicsviewer.shared.characters.data.api.Character>): List<Character> =
        characters.map { transformToCharacter(it) }

    private fun transformToCharacter(character: com.example.marvelcomicsviewer.shared.characters.data.api.Character): Character =
        Character(
            character.id,
            character.name,
            character.description,
            character.thumbnail.getFullPath()
        )

    private fun Image.getFullPath(): String = "$path.$extension"
}