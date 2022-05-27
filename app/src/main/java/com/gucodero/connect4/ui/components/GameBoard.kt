package com.gucodero.connect4.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun GameBoard(
    modifier: Modifier = Modifier,
    height: Int,
    width: Int,
    state: BoardState,
    matrix: Map<Pair<Int, Int>, Color>,
    onClick: (x: Int, y: Int) -> Unit
){
    var animationState: AnimationState by remember {
        mutableStateOf(AnimationState.None)
    }
    LaunchedEffect(Unit){
        if(state is BoardStateImpl){
            state.action = {
                animationState = it
            }
        }
    }
    val animationResult by animateBoard(animationState)
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(21.dp)
            )
            .padding(8.dp)
    ) {
        val size = maxWidth / width
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            repeat(height){ y ->
                Row {
                    repeat(width){ x ->
                        ItemBoard(
                            size = size,
                            color = animationResult?.matchEndColor(x, y) ?: matrix[x to y],
                            onClick = {
                                onClick(x, y)
                            },
                            colorAnimated = animationResult?.match(x, y)
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun animateBoard(
    data: AnimationState
): State<AnimationResult?> {
    val state: MutableState<AnimationResult?> = remember {
        mutableStateOf(null)
    }
    LaunchedEffect(data){
        when(data){
            is AnimationState.InProgress -> {
                (data.startY..data.endY).forEach { y ->
                    state.value = AnimationResult(
                        x = data.x,
                        y = y,
                        endY = data.endY,
                        color = data.color
                    )
                    delay(32)
                }
                state.value = null
            }
            else -> state.value = null
        }
    }
    return state
}

internal data class AnimationResult(
    val x: Int,
    val y: Int,
    val endY: Int,
    val color: Color
) {

    fun match(x: Int, y: Int): Color? {
        return if(this.x == x && this.y == y) color else null
    }

    fun matchEndColor(x: Int, y: Int): Color? {
        return if(this.x == x && this.endY == y)
            color
        else
            null
    }

}

internal sealed class AnimationState {
    data class InProgress(
        val x: Int,
        val startY: Int,
        val endY:Int,
        val color: Color
    ): AnimationState()
    object None: AnimationState()
}

interface BoardState {
    fun animateBoard(x: Int, startY: Int, endY: Int, color: Color)
}

internal class BoardStateImpl(
    var action: (AnimationState.InProgress) -> Unit = {}
): BoardState {

    override fun animateBoard(x: Int, startY: Int, endY: Int, color: Color) {
        action(
            AnimationState.InProgress(
                x = x,
                startY = startY,
                endY = endY,
                color = color
            )
        )
    }

}

@Composable
fun rememberBoardState(): BoardState {
    return remember {
        BoardStateImpl()
    }
}