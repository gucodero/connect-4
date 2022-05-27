package com.gucodero.connect4.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GameBackground(
    modifier: Modifier = Modifier,
    padding: Dp = 8.dp,
    content: @Composable ColumnScope.() -> Unit
){
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
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
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}