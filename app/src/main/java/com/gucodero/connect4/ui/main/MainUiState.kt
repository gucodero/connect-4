package com.gucodero.connect4.ui.main

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.Color
import com.gucodero.connect4.domain.game.entities.GameStatus
import com.gucodero.connect4.domain.player.entities.Player

data class MainUiState(
    val matrix: SnapshotStateMap<Pair<Int, Int>, Color> = mutableStateMapOf(),
    val height: Int,
    val width: Int,
    val gameStatus: GameStatus = GameStatus.None,
    val players: List<Player>,
    val currentPlayer: Player = Player.EMPTY
)