package com.starwarsapp.characters.ui.characterdetails

import com.starwarsapp.characters.ui.CharacterUiModel

sealed interface CharacterDetailsUiState {
    data object Loading : CharacterDetailsUiState
    data class Loaded(
        val character: CharacterUiModel,
    ) : CharacterDetailsUiState
    data object Error : CharacterDetailsUiState
}