package com.gucodero.connect4.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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