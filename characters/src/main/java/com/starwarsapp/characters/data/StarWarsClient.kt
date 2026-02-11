package com.starwarsapp.characters.data

import com.starwarsapp.characters.ui.CharacterId
import com.starwarsapp.characters.ui.FilmId
import com.starwarsapp.characters.ui.PlanetId
import com.starwarsapp.characters.ui.SpeciesId
import com.starwarsapp.characters.ui.StarshipId
import com.starwarsapp.characters.ui.VehicleId
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
        @Path("id") id: CharacterId,
    ): CharacterResult

    @GET("films/{id}")
    suspend fun getFilm(
        @Path("id") id: FilmId,
    ): FilmResult

    @GET("species/{id}")
    suspend fun getSpecies(
        @Path("id") id: SpeciesId,
    ): SpeciesResult

    @GET("planets/{id}")
    suspend fun getPlanet(
        @Path("id") id: PlanetId,
    ): PlanetResult

    @GET("starships/{id}")
    suspend fun getStarships(
        @Path("id") id: StarshipId,
    ): StarshipResult

    @GET("vehicles/{id}")
    suspend fun getVehicle(
        @Path("id") id: VehicleId,
    ): VehicleResult
}

object StarWarsApi {
    val starWarsClient: StarWarsClient by lazy {
        retrofit.create(StarWarsClient::class.java)
    }
}