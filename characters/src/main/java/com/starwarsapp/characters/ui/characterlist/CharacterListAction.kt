package com.starwarsapp.characters.ui.characterlist

sealed interface CharacterListAction {
    data object RetryClick : CharacterListAction
    data class CharacterClick(val id: Int) : CharacterListAction
}