package com.example.marvelcomicsviewer.shared.arch

import kotlinx.coroutines.flow.Flow

interface DataStore<in ID, out RESULT> {
    suspend fun fetch(id: ID): Flow<RESULT?>
    suspend fun fetchAll(): Flow<List<RESULT>>
}