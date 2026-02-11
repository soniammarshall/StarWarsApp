package com.starwarsapp.characters.ui.characterdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.starwarsapp.characters.ui.CharacterUiModel
import com.starwarsapp.characters.ui.characterlist.ErrorState
import com.starwarsapp.characters.ui.characterlist.LoadingState
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
        Text(
            text = "Characteristics",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .semantics { heading() }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            CharacterInfo(
                label = "Name",
                value = uiState.character.name,
            )
            CharacterInfo(
                label = "Birth Year",
                value = uiState.character.birthYear,
            )
            CharacterInfo(
                label = "Eye Color",
                value = uiState.character.eyeColor,
            )
            CharacterInfo(
                label = "Gender",
                value = uiState.character.gender,
            )
            CharacterInfo(
                label = "Hair Color",
                value = uiState.character.hairColor,
            )
            CharacterInfo(
                label = "Height",
                value = uiState.character.height,
            )
            CharacterInfo(
                label = "Mass",
                value = uiState.character.mass,
            )
            CharacterInfo(
                label = "Skin Color",
                value = uiState.character.skinColor,
            )
            CharacterInfo(
                label = "Homeworld",
                value = uiState.character.homeworld.toString(),
            )
        }
        Text(
            text = "Films",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .semantics { heading() }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            CharacterInfo(
                label = "Films",
                value = uiState.character.films.toString(),
            )
        }
        Text(
            text = "Species",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .semantics { heading() }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            CharacterInfo(
                label = "Species",
                value = uiState.character.species.toString(),
            )
        }
        Text(
            text = "Starships",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .semantics { heading() }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            CharacterInfo(
                label = "Starships",
                value = uiState.character.starships.toString(),
            )
        }
        Text(
            text = "Vehicles",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .semantics { heading() }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            CharacterInfo(
                label = "Vehicles",
                value = uiState.character.vehicles.toString(),
            )
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
    homeworld = 1,
    films = listOf(2,6,3,1,7),
    species = listOf(1),
    starships = listOf(12,22),
    vehicles = listOf(14,30),
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