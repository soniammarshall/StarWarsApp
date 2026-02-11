package com.starwarsapp.characters.ui.characterlist

import com.starwarsapp.characters.data.BasicCharacterResult

sealed interface CharacterListUiState {
    data object Loading : CharacterListUiState
    data class Loaded(
        val count: Int,
        val next: String?,
        val previous: String?,
        val characterList: List<BasicCharacterResult>
    ) : CharacterListUiState
    data object Error : CharacterListUiState
}