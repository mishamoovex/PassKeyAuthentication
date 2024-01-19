package com.aksio.core.designsystem.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomThemeColors(
    val buttonPrimary: Color,
    val buttonSecondary: Color,
    val buttonDisabled: Color,

    val textPrimary: Color,
    val textSecondary: Color,
    val textOnButton: Color,
    val textError: Color,
    val textHint: Color,
    val textLink: Color,

    val progressStatic: Color,
    val progressDynamic: Color,
    val progressBackground: Color,

    val surfacePrimary: Color,
    val surfaceSecondary: Color,

    val background: Color
)

private val Progress1 = Color(0xff757575)
private val Progress2 = Color(0xffEEEEEE)
private val Progress3 = Color(0xFFEFEFEF)

private val Background = Color(0xFFF9F8F7)
private val Surface = Color(0xFFFDFDFD)

private val TextPrimary = Color(0xFF243565)
private val TextSecondary = Color(0xFF989FB1)

private val Green = Color(0xFF16CA7F)
private val Yellow = Color(0xFFFFB336)
private val Blue = Color(0xFFA3C6FF)
private val BlueDark = Color(0xFF384BF8)
private val Red = Color(0xFFFE8157)

internal val LightColorPalette = CustomThemeColors(
    buttonPrimary = Green,
    buttonSecondary = Yellow,
    buttonDisabled = Progress3,

    textPrimary = TextPrimary,
    textSecondary = TextSecondary,
    textHint = TextSecondary,
    textOnButton = Color.White,
    textError = Red,
    textLink = BlueDark,

    progressStatic = Progress1,
    progressDynamic = Progress2,
    progressBackground = Progress3,

    surfacePrimary = Surface,
    surfaceSecondary = Surface,
    background = Background
)

internal val TextSelectionsColors = TextSelectionColors(
    handleColor = Blue,
    backgroundColor = Blue
)


@Immutable
internal object AppRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color = RippleTheme.defaultRippleColor(
        contentColor = Blue,
        lightTheme = !isSystemInDarkTheme()
    )

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = Blue,
        lightTheme = !isSystemInDarkTheme()
    )
}

val LocalCustomColors = staticCompositionLocalOf<CustomThemeColors> {
    error("No custom colors provided")
}

internal fun debugColors(
    debugColor: Color = Color.Magenta
) = ColorScheme(
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor,
    debugColor
)