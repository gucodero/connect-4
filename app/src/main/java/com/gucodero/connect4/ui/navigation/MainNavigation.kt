package com.gucodero.connect4.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gucodero.connect4.ui.main.MainScreen
import com.gucodero.connect4.ui.menu.MenuScreen

@Composable
fun MainNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "menu"
    ) {
        composable(
            route = "main/{player1}/{player2}",
            arguments = listOf(
                navArgument(
                    name = "player1"
                ) {
                    type = NavType.StringType
                }
            )
        ){
            val player1 = it.arguments?.getString("player1") ?: ""
            val player2 = it.arguments?.getString("player2") ?: ""
            MainScreen(player1, player2)
        }
        composable("menu"){
            MenuScreen(navController)
        }
    }
}