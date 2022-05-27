package com.gucodero.connect4.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gucodero.connect4.R

@Composable
fun PlayerBanner(
    modifier: Modifier = Modifier,
    name: String,
    color: Color,
    score: Int,
    isSelected: Boolean
){
    val shape = RoundedCornerShape(21.dp)
    val border = if(isSelected){
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
            text = name,
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
                text = score.toString(),
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
            color = color,
            border = borderInterpolate
        )
    }
}