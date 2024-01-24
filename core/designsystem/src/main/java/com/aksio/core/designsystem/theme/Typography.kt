package com.aksio.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aksio.core.designsystem.R


@Immutable
data class CustomTypography(
    val title: TextStyle,
    val bodyL: TextStyle,
    val bodyM: TextStyle,
    val bodyS: TextStyle,
    val button: TextStyle
)

internal val Typography = CustomTypography(
    title = TextStyle(
        color = Orange,
        fontFamily = FontFamily(Font(R.font.montserrat_bold, FontWeight.Bold)),
        fontSize = 32.sp
    ),
    bodyL = TextStyle(
        color = White,
        fontFamily = FontFamily(Font(R.font.montserrat_bold, FontWeight.Bold)),
        fontSize = 18.sp
    ),
    bodyM = TextStyle(
        color = White,
        fontFamily = FontFamily(Font(R.font.montserrat_medium, FontWeight.Medium)),
        fontSize = 16.sp
    ),
    bodyS = TextStyle(
        color = G100,
        fontFamily = FontFamily(Font(R.font.montserrat_medium, FontWeight.Medium)),
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_bold, FontWeight.Bold)),
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
)

val LocalCustomTypography = staticCompositionLocalOf<CustomTypography> {
    error("No custom typography provided")
}