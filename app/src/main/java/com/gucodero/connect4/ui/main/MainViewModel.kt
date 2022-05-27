package com.gucodero.connect4.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gucodero.connect4.domain.game.entities.Game
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.gucodero.connect4.domain.game.entities.GameStatus
import com.gucodero.connect4.domain.game.entities.GameTurn
import com.gucodero.connect4.domain.game.use_cases.NewGameUseCase
import com.gucodero.connect4.domain.game.use_cases.ResetGameUseCase
import com.gucodero.connect4.domain.player.entities.Player

class MainViewModel(
    private val player1: String,
    private val player2: String,
    private val newGameUseCase: NewGameUseCase = NewGameUseCase(),
    private val resetGameUseCase: ResetGameUseCase = ResetGameUseCase()
): ViewModel() {

    private var game: Game = newGameUseCase(player1, player2)

    private var _uiState by mutableStateOf(createNewState())
    val uiState get() = _uiState

    private fun createNewState(): MainUiState {
        return MainUiState(
            width = game.width,
            height = game.height,
            players = game.players.toList(),
            currentPlayer = game.playerTurn,
            gameStatus = game.status
        )
    }

    fun selectItem(x: Int): GameTurn?{
        if(game.status != GameStatus.InProgress && game.status != GameStatus.None){
            return null
        }
        game.selectItem(x)?.let { gameTurn ->
            uiState.matrix[gameTurn.x to gameTurn.y] = gameTurn.player.color
            refreshTurn()
            return gameTurn
        }
        return null
    }

    private fun refreshTurn(){
        _uiState = uiState.copy(
            currentPlayer = game.playerTurn,
            gameStatus = game.status
        )
    }

    fun resetGame(){
        game = resetGameUseCase(game)
        _uiState = createNewState()
    }

    fun newGame(){
        game = newGameUseCase(player1, player2)
        _uiState = createNewState()
    }

}