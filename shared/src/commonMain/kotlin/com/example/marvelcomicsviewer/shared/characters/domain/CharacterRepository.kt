package com.example.marvelcomicsviewer.shared.characters.domain

import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun fetchCharacters(): Flow<List<Character>>
}