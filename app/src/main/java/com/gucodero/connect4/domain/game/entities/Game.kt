package com.gucodero.connect4.domain.game.entities

import com.gucodero.connect4.domain.board.entities.Board
import com.gucodero.connect4.domain.board.entities.BoardItem
import com.gucodero.connect4.domain.board.entities.BoardSelectedItem
import com.gucodero.connect4.domain.player.entities.Player

data class Game(
    val players: List<Player>,
    val board: Board = Board(
        width = 7,
        height = 6,
        maxConnected = 4
    )
) {

    private var _playerTurn: Player = Player.EMPTY
    val playerTurn get() = _playerTurn

    private var _status: GameStatus
    val status get() = _status

    val matrix get() = board.matrix
    val height get() = board.height
    val width get() = board.width

    init {
        selectPlayer()
        _status = GameStatus.DEFAULT
    }

    private fun selectPlayer() {
        _playerTurn = if(_playerTurn === Player.EMPTY){
            players.first()
        } else {
            val indexCurrentPlayer = players.indexOf(_playerTurn)
            val indexNewPlayer = if(indexCurrentPlayer == players.lastIndex){
                0
            } else {
                indexCurrentPlayer + 1
            }
            players[indexNewPlayer]
        }
    }

    fun selectItem(x: Int): Boolean {
        val result = board.selectItem(x = x, player = playerTurn)
        if(result is BoardSelectedItem.Item){
            _status = if(result.isMaximum){
                GameStatus.Won(
                    x = result.x,
                    y = result.y,
                    player = playerTurn
                )
            } else {
                val player = playerTurn
                selectPlayer()
                if(board.isFull){
                    GameStatus.Tied(
                        x = result.x,
                        y = result.y,
                        newPlayer = playerTurn,
                        player = player
                    )
                } else {
                    GameStatus.InProgress(
                        x = result.x,
                        y = result.y,
                        newPlayer = playerTurn,
                        player = player
                    )
                }
            }
            return true
        }
        return false
    }

    fun isSelected(x: Int, y: Int): Boolean {
        return matrix[x][y] is BoardItem.Occupied
    }

}