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
    val results: List<Character>
)

@Serializable
@JsonIgnoreUnknownKeys
data class Character(
    val name: String,
    @SerialName("birth_year")
    val birthYear: String,
)

@Serializable
@JsonIgnoreUnknownKeys
data class CharacterResult(
    val name: String,
)