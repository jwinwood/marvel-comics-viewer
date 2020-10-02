package com.example.marvelcomicsviewer.androidApp

import android.app.Application
import com.example.marvelcomicsviewer.androidApp.characters.characterModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class MarvelComicsViewerApp : Application() {

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelComicsViewerApp)
            modules(listOf<Module>(
                commonModule,
                characterModule
            ))
        }
    }
}