package com.starwarsapp.characters.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.starwarsapp.uicomponents.components.PreviewSurface

@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = viewModel(factory = charactersViewModelFactory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CharactersScreenContent(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
fun CharactersScreenContent(
    uiState: CharactersUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = { innerPadding ->
            when (uiState) {
                CharactersUiState.Loading -> {
                    // TODO create standard loading state
                    LoadingState(modifier = Modifier.padding(innerPadding))
                }
                CharactersUiState.Error -> {
                    // TODO create standard error state
                    ErrorState(modifier = Modifier.padding(innerPadding))
                }
                CharactersUiState.Loaded -> {
                    CharactersList(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    )
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text("Loading")
    }
}

@Composable
fun ErrorState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text("Error")
    }
}

@Composable
fun CharactersList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text("Temporary text here")
    }
}

@PreviewLightDark
@Composable
internal fun PreviewCharactersScreenContentLightDark() {
    PreviewSurface {
        CharactersScreenContent(uiState = CharactersUiState.Loaded)
    }
}

@PreviewFontScale
@Composable
internal fun PreviewCharactersScreenContentFontScale() {
    PreviewSurface {
        CharactersScreenContent(uiState = CharactersUiState.Loaded)
    }
}

