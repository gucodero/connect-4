package com.gucodero.connect4.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    onChange: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Black, shape = MaterialTheme.shapes.small)
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
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.White,
            textColor = Color.White,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        placeholder = {
            Text(
                text = placeholder,
                color = Color.White.copy(alpha = 0.3f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
    )
}