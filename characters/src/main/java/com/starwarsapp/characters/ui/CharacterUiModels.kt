package com.starwarsapp.characters.ui

import com.starwarsapp.characters.data.FilmResult
import com.starwarsapp.characters.data.PlanetResult
import com.starwarsapp.characters.data.SpeciesResult
import com.starwarsapp.characters.data.StarshipResult
import com.starwarsapp.characters.data.VehicleResult

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
    val homeworld: PlanetResult?,
    val films: List<FilmResult>?,
    val species: List<SpeciesResult>?,
    val starships: List<StarshipResult>?,
    val vehicles: List<VehicleResult>?,
)
