package com.starwarsapp.characters.data

import com.starwarsapp.characters.ui.CharacterUiModel

fun mapToCharacterUiModel(
    character: CharacterResult,
    homeworld: PlanetResult?,
    films: List<FilmResult>,
    species: List<SpeciesResult>,
    starships: List<StarshipResult>,
    vehicles: List<VehicleResult>,
): CharacterUiModel {
    return CharacterUiModel(
        id = getId(character.url),
        name = character.name,
        birthYear = character.birthYear,
        eyeColor = character.eyeColor,
        gender = character.gender,
        hairColor = character.gender,
        height = character.height,
        mass = character.mass,
        skinColor = character.skinColor,
        homeworld = homeworld,
        films = films.ifEmpty { null },
        species = species.ifEmpty { null },
        starships = starships.ifEmpty { null },
        vehicles = vehicles.ifEmpty { null },
    )
}

fun getId(url: String): Int? {
    val regex = Regex("/(\\d+)/")
    val matchResult = regex.find(url) ?: return null
    return matchResult.value.split("/")[1].toInt()
}

fun getIdList(urls: List<String>): List<Int> {
    return urls.mapNotNull { getId(it) }
}