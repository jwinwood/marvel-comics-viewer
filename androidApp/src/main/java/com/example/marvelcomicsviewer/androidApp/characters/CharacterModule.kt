package com.example.marvelcomicsviewer.androidApp.characters

import com.example.marvelcomicsviewer.shared.characters.CharacterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val characterModule = module {
    single { CharacterRepository() }
    viewModel { CharactersViewModel(get()) }
}