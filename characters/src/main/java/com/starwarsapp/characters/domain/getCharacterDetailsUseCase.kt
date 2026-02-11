package com.starwarsapp.characters.domain

import com.starwarsapp.characters.data.StarWarsRepository
import com.starwarsapp.characters.data.getId
import com.starwarsapp.characters.data.getIdList
import com.starwarsapp.characters.data.mapToCharacterUiModel
import com.starwarsapp.characters.ui.CharacterId
import com.starwarsapp.characters.ui.CharacterUiModel

class GetCharacterDetailsUseCase(
    private val starWarsRepository: StarWarsRepository
) {
    suspend fun execute(id: CharacterId): CharacterUiModel {
        // TODO async
        val character = starWarsRepository.getCharacter(id)
        val homeworld = getId(character.homeworld)?.let { planetId ->
            starWarsRepository.getPlanet(planetId)
        }
        val films = getIdList(character.films).map { filmId ->
            starWarsRepository.getFilm(filmId)
        }
        val species = getIdList(character.species).map { speciesId ->
            starWarsRepository.getSpecies(speciesId)
        }
        val starships = getIdList(character.starships).map { starshipId ->
            starWarsRepository.getStarships(starshipId)
        }
        val vehicles = getIdList(character.vehicles).map { vehicleId ->
            starWarsRepository.getVehicle(vehicleId)
        }


        return mapToCharacterUiModel(
            character = character,
            homeworld = homeworld,
            films = films,
            species = species,
            starships = starships,
            vehicles = vehicles,
        )
    }
}