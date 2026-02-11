package com.starwarsapp.characters.ui.characterlist

import com.starwarsapp.characters.data.Character

sealed interface CharacterListUiState {
    data object Loading : CharacterListUiState
    data class Loaded(
        val count: Int,
        val next: String?,
        val previous: String?,
        val characterList: List<Character>
    ) : CharacterListUiState
    data object Error : CharacterListUiState
}