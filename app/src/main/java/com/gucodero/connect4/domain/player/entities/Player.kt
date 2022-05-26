package com.gucodero.connect4.domain.player.entities

import androidx.compose.ui.graphics.Color

data class Player(
    val name: String,
    val score: Int = 0,
    val color: Color
) {
    companion object {
        val EMPTY = Player(
            name = "",
            color = Color.Unspecified
        )
    }
}