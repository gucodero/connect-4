package com.gucodero.connect4.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
){
    Button(
        modifier = modifier
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
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}