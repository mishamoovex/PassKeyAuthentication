package com.aksio.core.designsystem.theme

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalCustomColors provides LightColorPalette) {
        MaterialTheme(
            colorScheme = debugColors(),
            typography = Typography
        ) {
            CompositionLocalProvider(
                LocalTextSelectionColors provides TextSelectionsColors,
                LocalRippleTheme provides AppRippleTheme,
                content = content
            )
        }
    }
}

object AppTheme {

    val colors: CustomThemeColors
        @Composable get() = LocalCustomColors.current

    val typography: Typography
        @Composable get() = MaterialTheme.typography
}