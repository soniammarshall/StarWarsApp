package com.starwarsapp.characters.data

import com.starwarsapp.characters.ui.CharacterUiModel

fun CharacterResult.toCharacterUiModel(): CharacterUiModel {
    return CharacterUiModel(
        id = getId(this.url),
        name = this.name,
        birthYear = this.birthYear,
        eyeColor = this.eyeColor,
        gender = this.gender,
        hairColor = this.gender,
        height = this.height,
        mass = this.mass,
        skinColor = this.skinColor,
        homeworld = getId(this.homeworld),
        films = getIdList(this.films),
        species = getIdList(this.species),
        starships = getIdList(this.starships),
        vehicles = getIdList(this.vehicles),
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