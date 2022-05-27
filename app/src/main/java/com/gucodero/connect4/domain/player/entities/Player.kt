package com.gucodero.connect4.domain.player.entities

import androidx.compose.ui.graphics.Color

data class Player(
    val id: Int,
    val name: String,
    val color: Color
) {

    private var _score = 0
    val score get() = _score

    companion object {
        val EMPTY = Player(
            id = -1,
            name = "",
            color = Color.Unspecified
        )
    }

    fun increaseScore(){
        _score++
    }

}