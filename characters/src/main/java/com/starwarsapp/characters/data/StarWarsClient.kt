package com.starwarsapp.characters.data

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://swapi.dev/api/"

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()

interface StarWarsClient {
    @GET("people")
    suspend fun getCharacterList(): CharacterListResult

    @GET("people/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
    ): CharacterResult
}

object StarWarsApi {
    val starWarsClient: StarWarsClient by lazy {
        retrofit.create(StarWarsClient::class.java)
    }
}