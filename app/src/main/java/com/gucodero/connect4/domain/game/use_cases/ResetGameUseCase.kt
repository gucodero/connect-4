package com.gucodero.connect4.domain.game.use_cases

import com.gucodero.connect4.domain.game.entities.Game

class ResetGameUseCase {

    operator fun invoke(lastGame: Game): Game {
        return Game(
            players = lastGame.players
        )
    }

}