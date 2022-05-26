package com.gucodero.connect4.domain.board.entities

sealed class BoardSelectedItem {
    object None: BoardSelectedItem()
    data class Item(
        val x: Int,
        val y: Int,
        val isMaximum: Boolean
    ): BoardSelectedItem()
}