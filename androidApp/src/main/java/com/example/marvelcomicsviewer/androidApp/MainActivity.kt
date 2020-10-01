package com.example.marvelcomicsviewer.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.example.marvelcomicsviewer.shared.Greeting
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.marvelcomicsviewer.androidApp.characters.CharactersViewModel
import com.example.marvelcomicsviewer.shared.characters.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val loadingSpinner: ProgressBar by lazy { findViewById(R.id.loading_spinner) }
    private val textView: TextView by lazy { findViewById(R.id.text_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: CharactersViewModel by viewModels()

        model.viewState.observe(this, {viewState ->
            loadingSpinner.isVisible = viewState.loading
            if (viewState.data.isNotEmpty()) {
                val character = viewState.data.first()
                textView.text = character.toString()
            }
        })

        model.fetchCharacters()
    }
}
