package com.example.marvelcomicsviewer.shared.arch

import kotlinx.coroutines.CoroutineDispatcher

abstract class BaseUseCase<in S : Any?, out T : Any?>(
    protected val dispatcher: CoroutineDispatcher
) : UseCase<S, T>