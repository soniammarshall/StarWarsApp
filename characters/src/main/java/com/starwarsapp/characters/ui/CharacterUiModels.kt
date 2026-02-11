package com.starwarsapp.characters.ui

typealias CharacterId = Int
typealias PlanetId = Int
typealias FilmId = Int
typealias SpeciesId = Int
typealias StarshipId = Int
typealias VehicleId = Int

data class CharacterUiModel(
    val id: CharacterId?,
    val name: String,
    val birthYear: String,
    val eyeColor: String,
    val gender: String,
    val hairColor: String,
    val height: String,
    val mass: String,
    val skinColor: String,
    val homeworld: PlanetId?,
    val films: List<FilmId>,
    val species: List<SpeciesId>,
    val starships: List<StarshipId>,
    val vehicles: List<VehicleId>,
)
