package com.gucodero.connect4.ui.menu

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.gucodero.connect4.ui.components.AppButton

@Composable
fun MenuScreen(navController: NavController){
    AppButton(text = "Jugar") {
        navController.navigate("main/Pablo/Celeste")
    }
}