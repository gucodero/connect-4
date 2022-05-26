package com.gucodero.connect4.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.gucodero.connect4.domain.game.entities.Game
import com.gucodero.connect4.domain.game.entities.GameStatus
import com.gucodero.connect4.domain.player.entities.Player
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainViewModel: ViewModel() {

    private val game: Game = Game(
        players = listOf(
            Player("Pablo", color = Color.Blue), Player("Francisco", color = Color.Red)
        )
    )

    private var _uiState by mutableStateOf(
        MainUiState(
            width = game.width,
            height = game.height,
            players = game.players
        )
    )
    val uiState get() = _uiState

    fun selectItem(x: Int){
        val result = game.selectItem(x)
        if(result){
            when(val status = game.status){
                is GameStatus.Won -> {
                    uiState.matrix["${status.x},${status.y}"] = status.player
                }
                is GameStatus.Tied -> {
                    uiState.matrix["${status.x},${status.y}"] = status.player
                }
                is GameStatus.InProgress -> {
                    uiState.matrix["${status.x},${status.y}"] = status.player
                }
            }
            _uiState = uiState.copy(
                gameStatus = game.status
            )
        }
    }

}