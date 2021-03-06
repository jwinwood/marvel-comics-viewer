package com.example.marvelcomicsviewer.shared.arch

import kotlinx.coroutines.flow.Flow

interface DataStore<out RESULT> {
    suspend fun fetch(): Flow<RESULT>
}