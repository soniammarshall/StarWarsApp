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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.starwarsapp.characters.ui.BasicCharacterUiModel
import com.starwarsapp.uicomponents.R
import com.starwarsapp.uicomponents.components.ErrorState
import com.starwarsapp.uicomponents.components.LoadingState
import com.starwarsapp.uicomponents.components.PreviewSurface
import com.starwarsapp.uicomponents.components.SWTopAppBar

@Composable
fun CharacterListScreen(
    navigateToCharacterDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharacterListViewModel = viewModel(factory = characterListViewModelFactory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is CharacterListEvent.NavigateToCharacterDetails -> {
                    navigateToCharacterDetails(event.id)
                }
            }
        }
    }

    CharacterListContent(
        uiState = uiState,
        onAction = viewModel::onAction,
        modifier = modifier,
    )
}

@Composable
fun CharacterListContent(
    uiState: CharacterListUiState,
    onAction: (CharacterListAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { SWTopAppBar(title = "Star Wars Characters") },
        modifier = modifier.fillMaxSize(),
        content = { innerPadding ->
            when (uiState) {
                CharacterListUiState.Loading -> {
                    LoadingState(modifier = Modifier.padding(innerPadding))
                }

                CharacterListUiState.Error -> {
                    ErrorState(
                        onRetryClick = { onAction(CharacterListAction.RetryClick) },
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                is CharacterListUiState.Loaded -> {
                    CharacterList(
                        uiState = uiState,
                        onAction = onAction,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    )
}

@Composable
fun CharacterList(
    uiState: CharacterListUiState.Loaded,
    onAction: (CharacterListAction) -> Unit,
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
                    CharacterRow(character = character, onAction = onAction)
                    if (index < uiState.characterList.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterRow(character: BasicCharacterUiModel, onAction: (CharacterListAction) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(
                onClick = {
                    character.id?.let {
                        onAction(CharacterListAction.CharacterClick(character.id))
                    }
                },
                onClickLabel = "open character details"
            )
            .semantics { role = Role.Button }
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
                maxLines = 2,
                overflow = Ellipsis
            )
            Text(
                text = "Gender: ${character.gender}",
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                overflow = Ellipsis
            )
        }
        // add favourites icon here
    }
}

val sampleLoadedUiState = CharacterListUiState.Loaded(
    count = 3,
    next = null,
    previous = null,
    characterList = listOf(
        BasicCharacterUiModel(
            name = "Luke Skywalker",
            gender = "male",
            id = 2,
        ),
        BasicCharacterUiModel(
            name = "C-3PO",
            gender = "n/a",
            id = 3,
        ),
        BasicCharacterUiModel(
            name = "Very Long Character Name For Example",
            gender = "unknown",
            id = 4,
        ),
    )
)

@PreviewLightDark
@Composable
internal fun PreviewCharacterListContentLightDark() {
    PreviewSurface {
        CharacterListContent(
            uiState = sampleLoadedUiState,
            onAction = {},
        )
    }
}

@PreviewFontScale
@Composable
internal fun PreviewCharacterListContentFontScale() {
    PreviewSurface {
        CharacterListContent(
            uiState = sampleLoadedUiState,
            onAction = {},
        )
    }
}

