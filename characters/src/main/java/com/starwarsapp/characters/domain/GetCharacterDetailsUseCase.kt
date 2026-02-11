package com.starwarsapp.characters.domain

import com.starwarsapp.characters.data.StarWarsRepository
import com.starwarsapp.characters.data.getId
import com.starwarsapp.characters.data.getIdList
import com.starwarsapp.characters.data.mapToCharacterUiModel
import com.starwarsapp.characters.ui.CharacterId
import com.starwarsapp.characters.ui.CharacterUiModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class GetCharacterDetailsUseCase(
    private val starWarsRepository: StarWarsRepository
) {
    suspend fun execute(id: CharacterId): CharacterUiModel {
        return withContext(IO) {
            val character = starWarsRepository.getCharacter(id)
            val homeworld = async {
                getId(character.homeworld)?.let { planetId ->
                    starWarsRepository.getPlanet(planetId)
                }
            }
            val films = async {
                getIdList(character.films).map { filmId ->
                    starWarsRepository.getFilm(filmId)
                }
            }
            val species = async {
                getIdList(character.species).map { speciesId ->
                    starWarsRepository.getSpecies(speciesId)
                }
            }
            val starships = async {
                getIdList(character.starships).map { starshipId ->
                    starWarsRepository.getStarships(starshipId)
                }
            }
            val vehicles = async {
                getIdList(character.vehicles).map { vehicleId ->
                    starWarsRepository.getVehicle(vehicleId)
                }
            }

            mapToCharacterUiModel(
                character = character,
                homeworld = homeworld.await(),
                films = films.await(),
                species = species.await(),
                starships = starships.await(),
                vehicles = vehicles.await(),
            )
        }
    }
}