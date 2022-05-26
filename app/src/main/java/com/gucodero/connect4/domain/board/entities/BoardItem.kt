package com.gucodero.connect4.domain.board.entities

import com.gucodero.connect4.domain.player.entities.Player

sealed class BoardItem {
    object Empty: BoardItem()
    data class Occupied(val player: Player): BoardItem()
}