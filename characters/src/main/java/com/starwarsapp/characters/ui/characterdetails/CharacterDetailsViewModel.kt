package com.starwarsapp.characters.ui.characterdetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.starwarsapp.characters.data.StarWarsRepositoryImpl
import com.starwarsapp.characters.domain.GetCharacterDetailsUseCase
import com.starwarsapp.characters.ui.CharacterId
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
) : ViewModel() {
    private val characterId: CharacterId = checkNotNull(savedStateHandle["id"])
    private val _uiState = MutableStateFlow<CharacterDetailsUiState>(CharacterDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _events = MutableSharedFlow<CharacterDetailsEvent>()
    val events = _events.asSharedFlow()

    init {
        loadScreen()
    }

    private fun loadScreen() {
        viewModelScope.launch {
            _uiState.value = CharacterDetailsUiState.Loading
            try {
                val result = getCharacterDetailsUseCase.execute(characterId)
                _uiState.value = CharacterDetailsUiState.Loaded(character = result)
            } catch (e: Exception) {
                Log.e("CharacterDetailsViewModel", "Error loading character details", e)
                _uiState.value = CharacterDetailsUiState.Error
            }
        }
    }

    fun onAction(action: CharacterDetailsAction) {
        when (action) {
            CharacterDetailsAction.BackClick -> {
                viewModelScope.launch {
                    _events.emit(CharacterDetailsEvent.NavigateBack)
                }
            }
            CharacterDetailsAction.RetryClick -> {
                loadScreen()
            }

        }
    }
}

val characterDetailsViewModelFactory : ViewModelProvider.Factory = viewModelFactory {
    initializer {
        // with more time would refactor to use dependency injection
        CharacterDetailsViewModel(
            savedStateHandle = createSavedStateHandle(),
            getCharacterDetailsUseCase = GetCharacterDetailsUseCase(
                starWarsRepository = StarWarsRepositoryImpl()
            ),
        )
    }
}