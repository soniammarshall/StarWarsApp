package com.starwarsapp.characters.ui.characterdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.starwarsapp.characters.data.FilmResult
import com.starwarsapp.characters.data.SpeciesResult
import com.starwarsapp.characters.data.StarshipResult
import com.starwarsapp.characters.data.VehicleResult
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
fun CharacteristicsSection(
    character: CharacterUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
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
            Column(
                modifier = Modifier.padding(vertical = 8.dp),
            ) {
                CharacterInfo(
                    label = "Name",
                    value = character.name,
                )
                CharacterInfo(
                    label = "Birth Year",
                    value = character.birthYear,
                )
                CharacterInfo(
                    label = "Eye Color",
                    value = character.eyeColor,
                )
                CharacterInfo(
                    label = "Gender",
                    value = character.gender,
                )
                CharacterInfo(
                    label = "Hair Color",
                    value = character.hairColor,
                )
                CharacterInfo(
                    label = "Height",
                    value = character.height,
                )
                CharacterInfo(
                    label = "Mass",
                    value = character.mass,
                )
                CharacterInfo(
                    label = "Skin Color",
                    value = character.skinColor,
                )
                character.homeworld?.let {
                    CharacterInfo(
                        label = "Homeworld",
                        value = it.name,
                    )
                }
            }
        }
    }
}

@Composable
fun FilmSection(
    films: List<FilmResult>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
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
            for (film in films) {
                Text(text = film.title)
            }
        }
    }
}

@Composable
fun SpeciesSection(
    species: List<SpeciesResult>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
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
            for (specie in species) {
                Text(text = specie.name)
            }
        }
    }
}

@Composable
fun StarshipsSection(
    starships: List<StarshipResult>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
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
            for (starship in starships) {
                Text(text = starship.name)
            }
        }
    }
}

@Composable
fun VehiclesSection(
    vehicles: List<VehicleResult>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
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
            for (vehicle in vehicles) {
                Text(text = vehicle.name)
            }
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
    homeworld = null,
    films = null,
    species = null,
    starships = null,
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