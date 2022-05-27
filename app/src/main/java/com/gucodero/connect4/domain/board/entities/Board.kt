package com.gucodero.connect4.domain.board.entities

import com.gucodero.connect4.domain.player.entities.Player

class Board(
    val width: Int,
    val height: Int,
    private val maxConnected: Int
) {

    val matrix: List<MutableList<BoardItem>>
    val isFull get() = matrix.find { row -> row.find { it is BoardItem.Empty } != null } == null

    init {
        matrix = mutableListOf<MutableList<BoardItem>>().apply {
            repeat(width){ _ ->
                mutableListOf<BoardItem>().let {
                    repeat(height){ _ ->
                        it.add(BoardItem.Empty)
                    }
                    add(it)
                }
            }
        }
    }

    fun selectItem(x: Int, player: Player): BoardSelectedItem {
        val index = matrix[x].lastIndexOf(BoardItem.Empty)
        if(index != -1){
            matrix[x][index] = BoardItem.Occupied(player)
            return BoardSelectedItem.Item(
                x = x,
                y = index,
                isMaximum = isMaximum(x, index, player)
            )
        }
        return BoardSelectedItem.None
    }

    private fun isMaximum(x: Int, y: Int, player: Player): Boolean {
        if(matrix[x].countMaxConsecutivePlayer(player) >= maxConnected){
            return true
        }
        if(getRow(y).countMaxConsecutivePlayer(player) >= maxConnected){
            return true
        }
        return false
    }

    private fun List<BoardItem>.countMaxConsecutivePlayer(player: Player): Int {
        var auxCounter = 0
        var counter = 0
        var newCount = true
        forEach { boardItem ->
            if(boardItem is BoardItem.Occupied && player.id == boardItem.player.id){
                if(newCount){
                    counter = 0
                    newCount = false
                }
                counter ++
            } else {
                if(counter > auxCounter){
                    auxCounter = counter
                }
                newCount = true
            }
        }
        return if(counter > auxCounter) counter else auxCounter
    }

    private fun getRow(y: Int): List<BoardItem> {
        return matrix.map { it[y] }
    }
}