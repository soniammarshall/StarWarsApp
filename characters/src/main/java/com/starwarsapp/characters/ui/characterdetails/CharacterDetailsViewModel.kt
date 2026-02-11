package com.starwarsapp.characters.ui.characterdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.starwarsapp.characters.data.StarWarsRepository
import com.starwarsapp.characters.data.StarWarsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val starWarsRepository: StarWarsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<CharacterDetailsUiState>(CharacterDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val id = 1
                val result = starWarsRepository.getCharacter(id)
                _uiState.value = CharacterDetailsUiState.Loaded(name = result.name)
            } catch (e: Exception) {
                Log.e("CharacterDetailsViewModel", "Error loading character details", e)
                _uiState.value = CharacterDetailsUiState.Error
            }
        }
    }
}

val characterDetailsViewModelFactory = viewModelFactory {
    initializer {
        CharacterDetailsViewModel(
            starWarsRepository = StarWarsRepositoryImpl()
        )
    }
}