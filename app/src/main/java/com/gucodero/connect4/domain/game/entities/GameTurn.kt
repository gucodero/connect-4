package com.gucodero.connect4.domain.game.entities

import com.gucodero.connect4.domain.player.entities.Player

data class GameTurn(
    val x: Int,
    val y: Int,
    val player: Player
)