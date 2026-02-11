package com.starwarsapp.characters.ui.characterdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.starwarsapp.characters.data.FilmResult
import com.starwarsapp.characters.data.PlanetResult
import com.starwarsapp.characters.data.StarshipResult
import com.starwarsapp.characters.ui.CharacterUiModel
import com.starwarsapp.uicomponents.components.ErrorState
import com.starwarsapp.uicomponents.components.LoadingState
import com.starwarsapp.uicomponents.components.PreviewSurface
import com.starwarsapp.uicomponents.components.SWTopAppBar

@Composable
fun CharacterDetailsScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailsViewModel = viewModel(factory = characterDetailsViewModelFactory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                CharacterDetailsEvent.NavigateBack -> {
                    navigateBack()
                }
            }
        }
    }

    CharacterDetailsContent(
        uiState = uiState,
        onAction = viewModel::onAction,
        modifier = modifier,
    )
}

@Composable
fun CharacterDetailsContent(
    uiState: CharacterDetailsUiState,
    onAction: (CharacterDetailsAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            SWTopAppBar(
                title = "Character Bio",
                showBackButton = true,
                onBackClick = {
                    onAction(CharacterDetailsAction.BackClick)
                }
            )
        },
        modifier = modifier.fillMaxSize(),
        content = { innerPadding ->
            when (uiState) {
                CharacterDetailsUiState.Loading -> {
                    LoadingState(modifier = Modifier.padding(innerPadding))
                }
                CharacterDetailsUiState.Error -> {
                    ErrorState(
                        onRetryClick = { onAction(CharacterDetailsAction.RetryClick) },
                        modifier = Modifier.padding(innerPadding))
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
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        CharacteristicsSection(uiState.character)
        uiState.character.films?.let {
            FilmSection(it)
        }
        uiState.character.species?.let {
            SpeciesSection(it)
        }
        uiState.character.starships?.let {
            StarshipsSection(it)
        }
        uiState.character.vehicles?.let {
            VehiclesSection(it)
        }
    }
}

@Composable
fun CharacterInfo(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 12.dp, vertical = 4.dp),
    ) {
        Text(text = label, style = MaterialTheme.typography.labelLarge)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}

val sampleCharacter = CharacterUiModel(
    id = 1,
    name = "Luke Skywalker",
    birthYear = "19BBY",
    eyeColor = "blue",
    gender = "male",
    hairColor = "blond",
    height = "172",
    mass = "77",
    skinColor = "fair",
    homeworld = PlanetResult(
        name = "Tatooine"
    ),
    films = listOf(
        FilmResult(
            title = "A New Hope"
        )
    ),
    species = null,
    starships = listOf(
        StarshipResult(
            name = "X-wing"
        )
    ),
    vehicles = null,
)

    @PreviewLightDark
    @Composable
    internal fun PreviewCharacterDetailsLightDark() {
        PreviewSurface {
            CharacterDetailsContent(
                uiState = CharacterDetailsUiState.Loaded(
                    character = sampleCharacter,
                ),
                onAction = {},
            )
        }
    }

    @PreviewFontScale
    @Composable
    internal fun PreviewCharacterDetailsFontScale() {
        PreviewSurface {
            CharacterDetailsContent(
                uiState = CharacterDetailsUiState.Loaded(
                    character = sampleCharacter,
                ),
                onAction = {},
            )
        }
    }