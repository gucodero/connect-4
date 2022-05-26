package com.gucodero.connect4.domain.game.entities

import com.gucodero.connect4.domain.player.entities.Player

sealed class GameStatus {
    data class Tied(
        val x: Int,
        val y: Int,
        val player: Player,
        val newPlayer: Player
    ): GameStatus()
    data class Won(
        val x: Int,
        val y: Int,
        val player: Player
    ): GameStatus()
    data class InProgress(
        val x: Int,
        val y: Int,
        val player: Player,
        val newPlayer: Player
    ): GameStatus()

    companion object {
        val DEFAULT = GameStatus.InProgress(
            x = -1,
            y = -1,
            newPlayer = Player.EMPTY,
            player = Player.EMPTY
        )
    }
}