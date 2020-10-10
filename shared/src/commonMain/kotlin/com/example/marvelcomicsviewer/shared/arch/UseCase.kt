package com.example.marvelcomicsviewer.shared.arch

import kotlinx.coroutines.flow.Flow

interface UseCase<in S : Any?, out T : Any?> {
    suspend fun invoke(param: S?): Flow<T>
}