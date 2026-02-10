package com.starwarsapp.characters.data

interface StarWarsRepository {
    suspend fun getCharacterList(): CharacterListResult
}

class StarWarsRepositoryImpl() : StarWarsRepository {
    override suspend fun getCharacterList(): CharacterListResult {
        return StarWarsApi.starWarsClient.getCharacterList()

    }
}