package com.starwarsapp.characters.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
data class CharacterListResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Character>
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class Character(
    val name: String,
    val height: String,
    val mass: String,
)