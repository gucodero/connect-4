package com.gucodero.connect4.domain.game.entities

import com.gucodero.connect4.domain.player.entities.Player

data class GameTurn(
    val x: Int,
    val y: Int,
    val player: Player
) {

    fun getId(): String = GameTurn.getId(x, y)

    companion object {
        fun getId(x: Int, y: Int): String = "$x,$y"
    }
}