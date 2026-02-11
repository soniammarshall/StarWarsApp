package com.starwarsapp.characters.data

interface StarWarsRepository {
    suspend fun getCharacterList(): CharacterListResult
    suspend fun getCharacter(id: Int) : CharacterResult
}

class StarWarsRepositoryImpl() : StarWarsRepository {
    override suspend fun getCharacterList(): CharacterListResult {
        return StarWarsApi.starWarsClient.getCharacterList()
    }

    override suspend fun getCharacter(id: Int) : CharacterResult {
        return StarWarsApi.starWarsClient.getCharacter(id)
    }

}