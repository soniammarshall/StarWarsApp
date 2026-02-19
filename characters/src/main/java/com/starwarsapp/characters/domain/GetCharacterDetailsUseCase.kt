package com.starwarsapp.characters.domain

import com.starwarsapp.characters.data.StarWarsRepository
import com.starwarsapp.characters.data.getId
import com.starwarsapp.characters.data.getIdList
import com.starwarsapp.characters.data.mapToCharacterUiModel
import com.starwarsapp.characters.ui.CharacterId
import com.starwarsapp.characters.ui.CharacterUiModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class GetCharacterDetailsUseCase(
    private val starWarsRepository: StarWarsRepository
) {
    suspend fun execute(id: CharacterId): CharacterUiModel {
        return withContext(IO) {
            val character = starWarsRepository.getCharacter(id)
            val homeworld = getId(character.homeworld)?.let { planetId ->
                async { starWarsRepository.getPlanet(planetId) }
            }
            val films = getIdList(character.films).map { filmId ->
                async { starWarsRepository.getFilm(filmId) }
            }
            val species = getIdList(character.species).map { speciesId ->
                async { starWarsRepository.getSpecies(speciesId) }
            }
            val starships = getIdList(character.starships).map { starshipId ->
                async { starWarsRepository.getStarships(starshipId) }
            }
            val vehicles = getIdList(character.vehicles).map { vehicleId ->
                async { starWarsRepository.getVehicle(vehicleId) }
            }

            mapToCharacterUiModel(
                character = character,
                homeworld = homeworld?.await(),
                films = films.awaitAll(),
                species = species.awaitAll(),
                starships = starships.awaitAll(),
                vehicles = vehicles.awaitAll(),
            )
        }
    }
}