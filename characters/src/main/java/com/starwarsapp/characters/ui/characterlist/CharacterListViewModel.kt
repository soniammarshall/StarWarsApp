package com.starwarsapp.characters.ui.characterlist

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

class CharacterListViewModel(
    private val starWarsRepository: StarWarsRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CharacterListUiState>(CharacterListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val result = starWarsRepository.getCharacterList()
                _uiState.value = CharacterListUiState.Loaded(
                    count = result.count,
                    next = result.next,
                    previous = result.previous,
                    characterList = result.results
                )
            } catch(e: Exception) {
                Log.e("CharactersViewModel", "Error loading character list", e)
                _uiState.value = CharacterListUiState.Error
            }
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