package com.example.marvelcomicsviewer.androidApp

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.marvelcomicsviewer.androidApp.characters.CharactersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val loadingSpinner: ProgressBar by lazy { findViewById(R.id.loading_spinner) }
    private val textView: TextView by lazy { findViewById(R.id.text_view) }

    private val model: CharactersViewModel by viewModel()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            model.viewState.collect { viewState ->
                loadingSpinner.isVisible = viewState.loading
                if (viewState.data.isNotEmpty()) {
                    val character = viewState.data.first()
                    textView.text = character.toString()
                }
            }
        }
    }
}
