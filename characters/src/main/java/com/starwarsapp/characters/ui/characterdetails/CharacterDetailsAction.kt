package com.starwarsapp.characters.ui.characterdetails

sealed interface CharacterDetailsAction {
    data object BackClick : CharacterDetailsAction
}