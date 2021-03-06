package com.gucodero.connect4.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gucodero.connect4.domain.game.entities.GameStatus
import com.gucodero.connect4.App
import com.gucodero.connect4.R
import com.gucodero.connect4.ui.components.*

@Composable
fun MainScreen(
    player1: String,
    player2: String,
    viewModel: MainViewModel = remember { MainViewModel(player1, player2) }
) = with(viewModel.uiState){
    when(gameStatus){
        GameStatus.Won -> {
            GameDialog(
                title = stringResource(id = R.string.won, currentPlayer.name.uppercase()),
                textButton = stringResource(id = R.string.retry)
            ) {
                viewModel.resetGame()
            }
        }
        GameStatus.Tied -> {
            GameDialog(
                title = stringResource(id = R.string.tied),
                textButton = stringResource(id = R.string.retry)
            ) {
                viewModel.resetGame()
            }
        }
        else -> {}
    }
    GameBackground {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlayerBanner(
                name = players[0].name,
                color = players[0].color,
                score = players[0].score,
                isSelected = players[0].id == currentPlayer.id,
                modifier = Modifier.weight(1.5f),
            )
            AppButton(
                text = stringResource(id = R.string.new_game),
                modifier = Modifier.weight(1.5f)
            ) {
                viewModel.newGame()
            }
            PlayerBanner(
                name = players[1].name,
                color = players[1].color,
                score = players[1].score,
                isSelected = players[1].id == currentPlayer.id,
                modifier = Modifier.weight(1.5f),
            )
        }
        val boardState = rememberBoardState()
        GameBoard(
            height = height,
            width = width,
            state = boardState,
            matrix = matrix,
            modifier = Modifier.weight(1f)
        ) { x, startY ->
            val gameTurn = viewModel.selectItem(x) ?: return@GameBoard
            boardState.animateBoard(
                x = x,
                startY = startY,
                endY = gameTurn.y,
                color = gameTurn.player.color
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview(){
    App {
        MainScreen("Gucoder1", "Gucoder2")
    }
}