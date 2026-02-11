package com.starwarsapp.characters.ui.characterdetails

sealed interface CharacterDetailsEvent {
    data object NavigateBack : CharacterDetailsEvent
}