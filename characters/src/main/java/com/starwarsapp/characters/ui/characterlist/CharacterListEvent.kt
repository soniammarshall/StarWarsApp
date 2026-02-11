package com.starwarsapp.characters.ui.characterlist

sealed interface CharacterListEvent {
    data class NavigateToCharacterDetails(val id: Int) : CharacterListEvent
}