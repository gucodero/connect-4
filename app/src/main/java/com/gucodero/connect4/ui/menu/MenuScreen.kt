package com.gucodero.connect4.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gucodero.connect4.R
import com.gucodero.connect4.ui.components.AppButton
import com.gucodero.connect4.ui.components.AppTextField
import com.gucodero.connect4.ui.components.GameBackground

@Composable
fun MenuScreen(navController: NavController){
    var player1 by remember {
        mutableStateOf("")
    }
    var player2 by remember {
        mutableStateOf("")
    }
    GameBackground(
        padding = 24.dp
    ) {
        val paddingTop = Modifier.padding(top = 32.dp)
        Text(
            text = stringResource(id = R.string.app_name),
            color = Color.White,
            fontSize = 32.sp
        )
        AppTextField(
            value = player1,
            placeholder = stringResource(id = R.string.player, 1),
            onChange = {
                player1 = it
            },
            modifier = paddingTop
        )
        AppTextField(
            value = player2,
            placeholder = stringResource(id = R.string.player, 2),
            onChange = {
                player2 = it
            },
            modifier = paddingTop
        )
        AppButton(
            text = stringResource(id = R.string.to_play),
            enabled = player1.isNotEmpty() && player2.isNotEmpty(),
            modifier = paddingTop.fillMaxWidth()
        ) {
            navController.navigate("main/$player1/$player2")
        }
    }
}