package com.starwarsapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.starwarsapp.characters.ui.characterdetails.CharacterDetailsScreen
import com.starwarsapp.characters.ui.characterlist.CharacterListScreen

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CharacterList.route,
    ) {
        composable(CharacterList.route) {
            CharacterListScreen(
                // TODO pass the id in
                navigateToCharacterDetails = {
                    navController.navigate(CharacterDetails.route)
                }
            )
        }
        composable(CharacterDetails.route) {
            CharacterDetailsScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}

interface NavigationDestination {
    val route: String
}

object CharacterList : NavigationDestination {
    override val route: String = "characterList"
}

object CharacterDetails : NavigationDestination {
    override val route: String = "characterDetails"
}