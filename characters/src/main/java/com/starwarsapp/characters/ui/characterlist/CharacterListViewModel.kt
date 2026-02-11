package com.starwarsapp.characters.ui.characterlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.starwarsapp.characters.data.StarWarsRepository
import com.starwarsapp.characters.data.StarWarsRepositoryImpl
import com.starwarsapp.characters.data.mapToBasicCharacterUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val starWarsRepository: StarWarsRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CharacterListUiState>(CharacterListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<CharacterListEvent>()
    val events = _events.asSharedFlow()


    init {
        loadScreen()
    }

    private fun loadScreen() {
        viewModelScope.launch {
            _uiState.value = CharacterListUiState.Loading
            try {
                val result = starWarsRepository.getCharacterList()
                _uiState.value = CharacterListUiState.Loaded(
                    count = result.count,
                    next = result.next,
                    previous = result.previous,
                    characterList = result.results.map { mapToBasicCharacterUiModel(it) }
                )
            } catch(e: Exception) {
                Log.e("CharactersViewModel", "Error loading character list", e)
                _uiState.value = CharacterListUiState.Error
            }
        }
    }

    fun onAction(action: CharacterListAction) {
        when (action) {
            is CharacterListAction.CharacterClick -> {
                viewModelScope.launch {
                    _events.emit(CharacterListEvent.NavigateToCharacterDetails(action.id))
                }
            }
            CharacterListAction.RetryClick -> {}
        }
    }
}

val characterListViewModelFactory = viewModelFactory {
    initializer {
        CharacterListViewModel(
            // with more time would refactor to use dependency injection
            starWarsRepository = StarWarsRepositoryImpl()
        )
    }
}