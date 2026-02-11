package com.starwarsapp.characters.ui.characterlist

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.starwarsapp.characters.data.Character
import com.starwarsapp.uicomponents.R
import com.starwarsapp.uicomponents.components.PreviewSurface
import com.starwarsapp.uicomponents.components.SWTopAppBar

@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterListViewModel = viewModel(factory = characterListViewModelFactory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CharacterListScreenContent(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
fun CharacterListScreenContent(
    uiState: CharacterListUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { SWTopAppBar(title = "Star Wars Characters", showBackButton = true, onBackClick = {}) },
        modifier = modifier.fillMaxSize(),
        content = { innerPadding ->
            when (uiState) {
                CharacterListUiState.Loading -> {
                    // TODO create standard loading state
                    LoadingState(modifier = Modifier.padding(innerPadding))
                }
                CharacterListUiState.Error -> {
                    // TODO create standard error state
                    ErrorState(modifier = Modifier.padding(innerPadding))
                }
                is CharacterListUiState.Loaded -> {
                    CharacterList(
                        uiState = uiState,
                        modifier = Modifier.padding(innerPadding),
                    )
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
fun CharacterList(
    uiState: CharacterListUiState.Loaded,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = "Total Characters: ${uiState.count}",
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = Ellipsis,
            modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 8.dp)
        )
        LazyColumn(
            modifier = Modifier.border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.medium
            )
        ) {
            uiState.characterList.forEachIndexed { index, character ->
                item {
                    CharacterRow(character)
                    if (index < uiState.characterList.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterRow(character: Character) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(
                onClick = {}
            )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_character),
            contentDescription = null,
            modifier = Modifier.padding(vertical = 4.dp).padding(end = 12.dp)
        )
        Column {
            Text(
                text = character.name,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = Ellipsis
            )
            Text(
                text = character.birthYear,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                overflow = Ellipsis
            )
        }
        // add favourites icon here
    }
}

val sampleLoadedUiState = CharacterListUiState.Loaded(
    count = 2,
    next = null,
    previous = null,
    characterList = listOf(
        Character(
            name = "Luke Skywalker",
            birthYear = "19 BBY"
        ),
        Character(
            name = "C-3PO",
            birthYear = "112BBY"
        ),
    )
)

@PreviewLightDark
@Composable
internal fun PreviewCharacterListScreenContentLightDark() {
    PreviewSurface {
        CharacterListScreenContent(
            uiState = sampleLoadedUiState
        )
    }
}

@PreviewFontScale
@Composable
internal fun PreviewCharacterListScreenContentFontScale() {
    PreviewSurface {
        CharacterListScreenContent(
            uiState = sampleLoadedUiState
        )
    }
}

