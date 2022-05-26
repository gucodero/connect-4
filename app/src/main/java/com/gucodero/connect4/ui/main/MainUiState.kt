package com.gucodero.connect4.ui.main

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.Color
import com.gucodero.connect4.domain.game.entities.GameStatus
import com.gucodero.connect4.domain.player.entities.Player

data class MainUiState(
    val matrix: SnapshotStateMap<String, Player> = mutableStateMapOf(),
    val height: Int,
    val width: Int,
    val gameStatus: GameStatus = GameStatus.DEFAULT,
    val players: List<Player>
) {

    fun getPlayer(x: Int, y: Int): Player? {
        return matrix["$x,$y"]
    }

    fun getColor(x: Int, y: Int): Color {
        return getPlayer(x, y)?.color ?: Color.Unspecified
    }

}