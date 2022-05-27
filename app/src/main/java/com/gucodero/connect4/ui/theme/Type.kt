package com.gucodero.connect4.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gucodero.connect4.R

val Typography = Typography(
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    defaultFontFamily = FontFamily(
        Font(R.font.press_start, FontWeight.W100),
        Font(R.font.press_start, FontWeight.W200),
        Font(R.font.press_start, FontWeight.W300),
        Font(R.font.press_start, FontWeight.W400),
        Font(R.font.press_start, FontWeight.W500),
        Font(R.font.press_start, FontWeight.W600),
        Font(R.font.press_start, FontWeight.W700),
        Font(R.font.press_start, FontWeight.W800),
        Font(R.font.press_start, FontWeight.W900)
    )
)