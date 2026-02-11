package com.starwarsapp.characters.ui.characterdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.starwarsapp.characters.data.FilmResult
import com.starwarsapp.characters.data.SpeciesResult
import com.starwarsapp.characters.data.StarshipResult
import com.starwarsapp.characters.data.VehicleResult
import com.starwarsapp.characters.ui.CharacterUiModel

@Composable
fun GenericSection(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .semantics { heading() }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun CharacteristicsSection(
    character: CharacterUiModel,
    modifier: Modifier = Modifier,
) {
    GenericSection(
        title = "Characteristics",
        modifier = modifier,
        content = {
            CharacterInfo(
                label = "Name",
                value = character.name,
            )
            CharacterInfo(
                label = "Birth Year",
                value = character.birthYear,
            )
            CharacterInfo(
                label = "Eye Color",
                value = character.eyeColor,
            )
            CharacterInfo(
                label = "Gender",
                value = character.gender,
            )
            CharacterInfo(
                label = "Hair Color",
                value = character.hairColor,
            )
            CharacterInfo(
                label = "Height",
                value = character.height,
            )
            CharacterInfo(
                label = "Mass",
                value = character.mass,
            )
            CharacterInfo(
                label = "Skin Color",
                value = character.skinColor,
            )
            character.homeworld?.let {
                CharacterInfo(
                    label = "Homeworld",
                    value = it.name,
                )
            }
        }
    )
}

@Composable
fun FilmSection(
    films: List<FilmResult>,
    modifier: Modifier = Modifier,
) {
    GenericSection(
        title = "Films",
        modifier = modifier,
        content = {
            for (film in films) {
                Text(text = film.title, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
            }
        },

    )
//    Column(modifier = modifier) {
//        Text(
//            text = "Films",
//            style = MaterialTheme.typography.titleMedium,
//            modifier = Modifier
//                .padding(horizontal = 12.dp)
//                .semantics { heading() }
//        )
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp)
//        ) {
//            Column(
//                modifier = Modifier.padding(vertical = 8.dp),
//            ) {
//                for (film in films) {
//                    Text(text = film.title)
//                }
//            }
//        }
//    }
}

@Composable
fun SpeciesSection(
    species: List<SpeciesResult>,
    modifier: Modifier = Modifier,
) {
    GenericSection(
        title = "Species",
        modifier = modifier,
        content = {
            for (specie in species) {
                Text(text = specie.name, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
            }
        }
    )
}

@Composable
fun StarshipsSection(
    starships: List<StarshipResult>,
    modifier: Modifier = Modifier,
) {
    GenericSection(
        title = "Starships",
        modifier = modifier,
        content = {
            for (starship in starships) {
                Text(text = starship.name, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
            }
        }
    )
}

@Composable
fun VehiclesSection(
    vehicles: List<VehicleResult>,
    modifier: Modifier = Modifier,
) {
    GenericSection(
        title = "Vehicles",
    modifier = modifier,
    content = {
            for (vehicle in vehicles) {
                Text(text = vehicle.name, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
            }
        }
    )
}