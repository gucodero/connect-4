package com.gucodero.connect4.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.gucodero.connect4.domain.game.entities.GameStatus
import com.gucodero.connect4.ui.App

@Composable
fun MainScreen(viewModel: MainViewModel = remember { MainViewModel() }) = with(viewModel.uiState){
    when(gameStatus){
        is GameStatus.Won -> {
            Dialog(onDismissRequest = { /*TODO*/ }) {
                Text(text = "Gano ${gameStatus.player.name}")
            }
        }
        is GameStatus.Tied -> {
            Dialog(onDismissRequest = { /*TODO*/ }) {
                Text(text = "Empate")
            }
        }
        else -> {}
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth()
        ) {
            val size = maxWidth / width
            Column {
                repeat(height){ y ->
                    Row {
                        repeat(width){ x ->
                            Box(
                                modifier = Modifier
                                    .size(size)
                                    .padding(4.dp)
                                    .background(
                                        if (getPlayer(x, y) != null) {
                                            getColor(x, y)
                                        } else {
                                            Color.LightGray
                                        },
                                        CircleShape
                                    )
                                    .clip(CircleShape)
                                    .clickable {
                                        viewModel.selectItem(x)
                                    }
                            )
                        }
                    }
                }
            }
        }
        players.forEach {
            Text(
                text = it.name,
                color = it.color,
                fontSize = 24.sp
            )
        }
    }
}


@Preview
@Composable
fun MainScreenPreview(){
    App {
        MainScreen()
    }
}