package com.starwarsapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.starwarsapp.characters.ui.CharacterId
import com.starwarsapp.characters.ui.characterdetails.CharacterDetailsScreen
import com.starwarsapp.characters.ui.characterlist.CharacterListScreen
import kotlinx.serialization.Serializable

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.CharacterList,
    ) {
        composable<NavigationDestination.CharacterList> {
            CharacterListScreen(
                navigateToCharacterDetails = { id ->
                    navController.navigate(NavigationDestination.CharacterDetails(id))
                }
            )
        }
        composable<NavigationDestination.CharacterDetails> {
            CharacterDetailsScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}

sealed class NavigationDestination {

    @Serializable
    data object CharacterList : NavigationDestination()

    @Serializable
    data class CharacterDetails(val id: CharacterId) : NavigationDestination()
}