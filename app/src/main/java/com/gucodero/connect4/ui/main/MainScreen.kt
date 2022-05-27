package com.gucodero.connect4.ui.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.gucodero.connect4.domain.game.entities.GameStatus
import com.gucodero.connect4.App
import com.gucodero.connect4.R
import com.gucodero.connect4.domain.game.entities.GameTurn
import com.gucodero.connect4.domain.player.entities.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModel: MainViewModel = remember { MainViewModel() }) = with(viewModel.uiState){
    when(gameStatus){
        GameStatus.Won -> {
            GameDialog(
                title = stringResource(id = R.string.won, currentPlayer.name.uppercase()),
                textButton = stringResource(id = R.string.retry)
            ) {
                viewModel.resetGame()
            }
        }
        GameStatus.Tied -> {
            GameDialog(
                title = stringResource(id = R.string.tied),
                textButton = stringResource(id = R.string.retry)
            ) {
                viewModel.resetGame()
            }
        }
        else -> {}
    }

    val coroutine = rememberCoroutineScope()

    var startTurn: GameTurn? by remember {
        mutableStateOf(null)
    }
    var animateTurn: GameTurn? by remember {
        mutableStateOf(null)
    }

    BoxWithConstraints {
        val finalX = LocalDensity.current.run { maxWidth.toPx() }
        val finalY = LocalDensity.current.run { maxHeight.toPx() }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF792974),
                            Color(0xFF3a1664)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(finalX / 2, finalY / 2)
                    )
                )
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PlayerBanner(
                    player = players[0],
                    modifier = Modifier.weight(1.5f),
                    currentPlayer = currentPlayer
                )
                Button(
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(8.dp)
                        .border(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFaf4dee),
                                    Color(0xFF5bc2fb)
                                )
                            ),
                            width = 2.dp,
                            shape = MaterialTheme.shapes.small
                        ),
                    onClick = {
                        viewModel.newGame()
                    },
                ) {
                    Text(
                        text = stringResource(id = R.string.new_game),
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                PlayerBanner(
                    player = players[1],
                    modifier = Modifier.weight(1.5f),
                    currentPlayer = currentPlayer
                )
            }
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
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
                                    color = startTurn.let {
                                        if(it != null){
                                            if(it.x == x && it.y == y){
                                                null
                                            } else {
                                                getColor(x, y)
                                            }
                                        } else {
                                            getColor(x, y)
                                        }
                                    },
                                    onClick = {
                                        viewModel
                                            .selectItem(x)
                                            ?.let { gameTurn ->
                                                coroutine.launch {
                                                    startTurn = gameTurn
                                                    (0..gameTurn.y).forEach { y ->
                                                        animateTurn = GameTurn(
                                                            x = gameTurn.x,
                                                            y = y,
                                                            player = gameTurn.player
                                                        )
                                                        delay(32)
                                                    }
                                                    startTurn = null
                                                    animateTurn = null
                                                }
                                            }
                                    },
                                    colorAnimated = animateTurn?.let {
                                        if(it.x == x && it.y == y){
                                            it.player.color
                                        } else {
                                            null
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerBanner(
    modifier: Modifier = Modifier,
    player: Player,
    currentPlayer: Player,
){
    val shape = RoundedCornerShape(21.dp)
    val border = if(currentPlayer.id == player.id){
        5.dp
    } else {
        1.dp
    }
    val borderInterpolate by animateDpAsState(targetValue = border, tween(durationMillis = 300))
    Column(
        modifier = modifier
            .shadow(
                elevation = 12.dp,
                shape = shape
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF632fd7),
                        Color(0xFF7c42fe),
                        Color(0xFF6b31e9)
                    )
                ),
                shape = shape
            )
            .padding(horizontal = 14.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = player.name,
            color = Color.White,
            fontSize = 10.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Black,
                    shape = shape
                )
                .border(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFaf4dee),
                            Color(0xFF5bc2fb)
                        )
                    ),
                    width = 2.dp,
                    shape = shape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = player.score.toString(),
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.padding(
                    top = 26.dp,
                    bottom = 12.dp
                )
            )
        }
        Text(
            text = stringResource(id = R.string.score),
            color = Color.White,
            fontSize = 10.sp,
            modifier = Modifier.padding(top = 12.dp)
        )
        ItemBoard(
            size = 32.dp,
            color = player.color,
            border = borderInterpolate
        )
    }
}

@Composable
fun ItemBoard(
    size: Dp,
    color: Color?,
    border: Dp = 2.dp,
    onClick: (() -> Unit)? = null,
    colorAnimated: Color? = null
) {
    val animateColor = colorAnimated
        ?: animateColorAsState(
            targetValue = color ?: Color.LightGray.copy(alpha = 0.4f),
            tween(
                durationMillis = 300
            )
        ).value
    Box(
        modifier = Modifier
            .size(size)
            .padding(4.dp)
            .background(
                color = animateColor,
                CircleShape
            )
            .border(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFaf4dee),
                        Color(0xFF5bc2fb)
                    )
                ),
                width = border,
                shape = CircleShape
            )
            .clip(CircleShape)
            .run {
                if(onClick != null){
                    clickable(onClick = onClick)
                } else {
                    this
                }
            }
    )
}

@Composable
fun GameDialog(
    title: String,
    textButton: String,
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = {  }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 21.dp)
            )
            Button(
                onClick = onClick,
                modifier = Modifier.border(
                    width = 2.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFaf4dee),
                            Color(0xFF5bc2fb)
                        )
                    ),
                    shape = MaterialTheme.shapes.small
                ),
            ) {
                Text(
                    text = textButton,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview(){
    App {
        MainScreen()
    }
}