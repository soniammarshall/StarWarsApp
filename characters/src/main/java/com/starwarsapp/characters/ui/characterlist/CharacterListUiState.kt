package com.starwarsapp.characters.ui.characterlist

import com.starwarsapp.characters.ui.BasicCharacterUiModel

sealed interface CharacterListUiState {
    data object Loading : CharacterListUiState
    data class Loaded(
        val count: Int,
        val next: String?,
        val previous: String?,
        val characterList: List<BasicCharacterUiModel>
    ) : CharacterListUiState
    data object Error : CharacterListUiState
}