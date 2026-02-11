package com.starwarsapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.starwarsapp.characters.ui.characterlist.CharacterListScreen

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Characters.route,
    ) {
        composable(Characters.route) {
            CharacterListScreen()
        }
    }

}

interface NavigationDestination {
    val route: String
}

object Characters : NavigationDestination {
    override val route: String = "characters"
}