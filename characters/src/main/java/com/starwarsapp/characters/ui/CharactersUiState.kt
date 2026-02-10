package com.starwarsapp.characters.ui

sealed interface CharactersUiState {
    data object Loading : CharactersUiState
    data object Loaded : CharactersUiState
    data object Error : CharactersUiState
}