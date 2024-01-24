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
    val onPrimaryButton: Color,
    val onPrimaryButtonDisabled: Color,
    val buttonSecondary: Color,
    val onSecondaryButton: Color,
    val onSecondaryButtonDisabled: Color,
    val buttonDisabled: Color,

    val textHilt: Color,
    val textHintFocused: Color,

    val textIndicator: Color,
    val textIndicatorFocused: Color,

    val progressStatic: Color,
    val progressDynamic: Color,
    val progressBackground: Color,

    val surface: Color,
    val background: Color,
    val error: Color
)

internal val Progress1 = Color(0xff757575)
internal val Progress2 = Color(0xffEEEEEE)
internal val Progress3 = Color(0xFFEFEFEF)

internal val White = Color(0xFFE7E7E7)
internal val G100 = Color(0xFF9D9D9D)
internal val G200 = Color(0xFF747474)
internal val G300 = Color(0xFF393939)
internal val G400 = Color(0xFF131313)
internal val G500 = Color(0xFF0C0C0C)
internal val Black = Color(0xFF070707)

internal val YellowLight = Color(0xFFF9DB6A)
internal val Yellow = Color(0xFFF8D44C)
internal val YellowDark = Color(0xFFAE9435)

internal val OrangeLight = Color(0xFFEE8671)
internal val Orange = Color(0xFFEB6D54)

internal val Red = Color(0xFFE3262F)


internal val LightColorPalette = CustomThemeColors(
    buttonPrimary = Yellow,
    onPrimaryButton = Black,
    onPrimaryButtonDisabled = G100,
    buttonSecondary = Black,
    onSecondaryButton = Yellow,
    onSecondaryButtonDisabled = G200,
    buttonDisabled = G300,

    textHilt = G200,
    textHintFocused = G300,

    textIndicator = G200,
    textIndicatorFocused = Yellow,

    progressStatic = Progress1,
    progressDynamic = Progress2,
    progressBackground = Progress3,

    surface = G500,
    background = Black,
    error = Red
)

internal val TextSelectionsColors = TextSelectionColors(
    handleColor = YellowLight,
    backgroundColor = YellowLight
)


@Immutable
internal object AppRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color = RippleTheme.defaultRippleColor(
        contentColor = YellowLight,
        lightTheme = !isSystemInDarkTheme()
    )

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = YellowLight,
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