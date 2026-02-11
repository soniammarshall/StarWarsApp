package com.starwarsapp.characters.ui

import com.starwarsapp.characters.data.Character

sealed interface CharactersUiState {
    data object Loading : CharactersUiState
    data class Loaded(
        val count: Int,
        val next: String?,
        val previous: String?,
        val characterList: List<Character>
    ) : CharactersUiState
    data object Error : CharactersUiState
}