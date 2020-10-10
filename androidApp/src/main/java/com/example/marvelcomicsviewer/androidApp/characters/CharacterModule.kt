package com.example.marvelcomicsviewer.androidApp.characters

import com.example.marvelcomicsviewer.shared.characters.data.CharacterRepositoryImpl
import com.example.marvelcomicsviewer.shared.characters.domain.CharacterRepository
import com.example.marvelcomicsviewer.shared.characters.domain.FetchAllCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val characterModule = module {
    single { FetchAllCharactersUseCase(Dispatchers.IO, get()) }
    single<CharacterRepository> { CharacterRepositoryImpl() }
    viewModel { CharactersViewModel(get()) }
}