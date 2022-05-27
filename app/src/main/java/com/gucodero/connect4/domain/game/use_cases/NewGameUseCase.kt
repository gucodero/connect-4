package com.gucodero.connect4.domain.game.use_cases

import androidx.compose.ui.graphics.Color
import com.gucodero.connect4.domain.game.entities.Game
import com.gucodero.connect4.domain.player.entities.Player

class NewGameUseCase {

    operator fun invoke(vararg players: String): Game {
        return Game(
            players = players.mapIndexed { index, name ->
                Player(
                    id = index,
                    name = name,
                    color = COLOR_PLAYER[index]
                )
            }
        )
    }

    companion object {
        val COLOR_PLAYER = listOf(Color(0xFFe3740b), Color(0xFF0b53e3))
    }

}