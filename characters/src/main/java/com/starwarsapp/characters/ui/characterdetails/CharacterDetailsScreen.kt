package com.starwarsapp.characters.ui.characterdetails

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
import com.starwarsapp.characters.ui.characterlist.ErrorState
import com.starwarsapp.characters.ui.characterlist.LoadingState
import com.starwarsapp.uicomponents.components.PreviewSurface
import com.starwarsapp.uicomponents.components.SWTopAppBar

@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailsViewModel = viewModel(factory = characterDetailsViewModelFactory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CharacterDetailsContent(
        uiState = uiState,
        modifier = modifier,
    )
}

@Composable
fun CharacterDetailsContent(
    uiState: CharacterDetailsUiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { SWTopAppBar(title = "Character Bio", showBackButton = true, onBackClick = {}) },
        modifier = modifier.fillMaxSize(),
        content = { innerPadding ->
            when (uiState) {
                CharacterDetailsUiState.Loading -> {
                    LoadingState(modifier = Modifier.padding(innerPadding))
                }
                CharacterDetailsUiState.Error -> {
                    ErrorState(modifier = Modifier.padding(innerPadding))
                }
                is CharacterDetailsUiState.Loaded -> {
                    CharacterDetails(
                        uiState = uiState,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    )
}

@Composable
fun CharacterDetails(
    uiState: CharacterDetailsUiState.Loaded,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Name: ${uiState.name}")
    }
}

@PreviewLightDark
@Composable
internal fun PreviewCharacterDetailsLightDark() {
    PreviewSurface {
        CharacterDetailsContent(
            uiState = CharacterDetailsUiState.Loaded(name = "Luke Skywalker")
        )
    }
}

@PreviewFontScale
@Composable
internal fun PreviewCharacterDetailsFontScale() {
    PreviewSurface {
        CharacterDetailsContent(
            uiState = CharacterDetailsUiState.Loaded(name = "Luke Skywalker")
        )
    }
}