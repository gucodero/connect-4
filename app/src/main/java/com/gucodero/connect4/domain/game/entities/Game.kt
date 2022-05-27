package com.gucodero.connect4.domain.game.entities

import com.gucodero.connect4.domain.board.entities.Board
import com.gucodero.connect4.domain.board.entities.BoardItem
import com.gucodero.connect4.domain.board.entities.BoardSelectedItem
import com.gucodero.connect4.domain.player.entities.Player

data class Game(
    val players: List<Player>,
    val board: Board = Board(
        width = 6,
        height = 6,
        maxConnected = 4
    )
) {

    private var _playerTurn: Player = Player.EMPTY
    val playerTurn get() = _playerTurn

    private var _status: GameStatus
    val status get() = _status

    val height get() = board.height
    val width get() = board.width

    init {
        selectPlayer()
        _status = GameStatus.None
    }

    private fun selectPlayer() {
        _playerTurn = if(_playerTurn.id == Player.EMPTY.id){
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

    fun selectItem(x: Int): GameTurn? {
        val result = board.selectItem(x = x, player = playerTurn)
        if(result is BoardSelectedItem.Item){
            return if(result.isMaximum){
                playerTurn.increaseScore()
                _status = GameStatus.Won
                GameTurn(
                    x = result.x,
                    y = result.y,
                    player = playerTurn
                )
            } else {
                val player = playerTurn
                selectPlayer()
                if(board.isFull){
                    player.increaseScore()
                    playerTurn.increaseScore()
                    _status = GameStatus.Tied
                    GameTurn(
                        x = result.x,
                        y = result.y,
                        player = player
                    )
                } else {
                    _status = GameStatus.InProgress
                    GameTurn(
                        x = result.x,
                        y = result.y,
                        player = player
                    )
                }
            }
        }
        return null
    }

}