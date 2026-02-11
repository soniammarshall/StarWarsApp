package com.starwarsapp.characters.data

import com.starwarsapp.characters.ui.CharacterId
import com.starwarsapp.characters.ui.FilmId
import com.starwarsapp.characters.ui.PlanetId
import com.starwarsapp.characters.ui.SpeciesId
import com.starwarsapp.characters.ui.StarshipId
import com.starwarsapp.characters.ui.VehicleId

interface StarWarsRepository {
    suspend fun getCharacterList(): CharacterListResult
    suspend fun getCharacter(id: CharacterId) : CharacterResult
    suspend fun getFilm(id: FilmId) : FilmResult
    suspend fun getSpecies(id: SpeciesId) : SpeciesResult
    suspend fun getPlanet(id: PlanetId) : PlanetResult
    suspend fun getStarships(id: StarshipId) : StarshipResult
    suspend fun getVehicle(id: VehicleId) : VehicleResult
}

class StarWarsRepositoryImpl() : StarWarsRepository {
    override suspend fun getCharacterList(): CharacterListResult {
        return StarWarsApi.starWarsClient.getCharacterList()
    }

    override suspend fun getCharacter(id: CharacterId) : CharacterResult {
        return StarWarsApi.starWarsClient.getCharacter(id)
    }

    override suspend fun getFilm(id: FilmId): FilmResult {
        return StarWarsApi.starWarsClient.getFilm(id)
    }

    override suspend fun getSpecies(id: SpeciesId): SpeciesResult {
        return StarWarsApi.starWarsClient.getSpecies(id)
    }

    override suspend fun getPlanet(id: PlanetId): PlanetResult {
        return StarWarsApi.starWarsClient.getPlanet(id)
    }

    override suspend fun getStarships(id: StarshipId): StarshipResult {
        return StarWarsApi.starWarsClient.getStarships(id)
    }

    override suspend fun getVehicle(id: VehicleId): VehicleResult {
        return StarWarsApi.starWarsClient.getVehicle(id)
    }
}