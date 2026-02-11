package com.starwarsapp.characters.ui.characterdetails

sealed interface CharacterDetailsUiState {
    data object Loading : CharacterDetailsUiState
    data class Loaded(
        val name: String,
    ) : CharacterDetailsUiState
    data object Error : CharacterDetailsUiState
}