package com.starwarsapp.characters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _uiState.value = CharactersUiState.Loaded
            } catch(e: Exception) {
                _uiState.value = CharactersUiState.Error
            }
        }
    }
}

val charactersViewModelFactory = viewModelFactory {
    initializer {
        CharactersViewModel()
    }
}