@file:OptIn(ExperimentalSerializationApi::class)

package com.starwarsapp.characters.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
data class CharacterListResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BasicCharacterResult>
)

@Serializable
@JsonIgnoreUnknownKeys
data class BasicCharacterResult(
    val name: String,
    @SerialName("birth_year")
    val birthYear: String,
    val url: String,
)

@Serializable
@JsonIgnoreUnknownKeys
data class CharacterResult(
    val name: String,
    @SerialName("birth_year")
    val birthYear: String,
    @SerialName("eye_color")
    val eyeColor: String,
    val gender: String,
    @SerialName("hair_color")
    val hairColor: String,
    val height: String,
    val mass: String,
    @SerialName("skin_color")
    val skinColor: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val url: String,
)

@Serializable
@JsonIgnoreUnknownKeys
data class FilmResult(
    val title: String,
)

@Serializable
@JsonIgnoreUnknownKeys
data class PlanetResult(
    val name: String,
)

@Serializable
@JsonIgnoreUnknownKeys
data class SpeciesResult(
    val name: String,
)

@Serializable
@JsonIgnoreUnknownKeys
data class StarshipResult(
    val name: String,
)

@Serializable
@JsonIgnoreUnknownKeys
data class VehicleResult(
    val name: String,
)