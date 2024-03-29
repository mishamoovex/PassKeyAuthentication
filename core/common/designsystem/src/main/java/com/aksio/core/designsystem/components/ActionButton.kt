package com.aksio.core.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aksio.core.designsystem.theme.AppTheme

@Composable
private fun ActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    border: BorderStroke? = null,
    shape: Shape = RoundedCornerShape(16.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = AppTheme.colors.buttonPrimary,
        contentColor = AppTheme.colors.onPrimaryButton,
        disabledContainerColor = AppTheme.colors.buttonDisabled,
        disabledContentColor = AppTheme.colors.onPrimaryButtonDisabled
    ),
    content: @Composable () -> Unit
) {
    val shouldEnable = if (isLoading) true else isEnabled
    val action = if (isLoading) { -> } else onClick

    Button(
        onClick = action,
        enabled = shouldEnable,
        contentPadding = PaddingValues(0.dp),
        shape = shape,
        colors = colors,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        border = border
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            this@Button.AnimatedVisibility(
                visible = isLoading,

                ) {
                SpinningProgressIndicator(
                    modifier = Modifier.size(20.dp)
                )
            }
            this@Button.AnimatedVisibility(
                visible = !isLoading,
            ) {
                content()
            }
        }
    }
}

@Composable
fun TextActionButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = AppTheme.colors.buttonPrimary,
        contentColor = AppTheme.colors.onPrimaryButton,
        disabledContainerColor = AppTheme.colors.buttonDisabled,
        disabledContentColor = AppTheme.colors.onPrimaryButtonDisabled
    ),
    shape: Shape = RoundedCornerShape(50),
    textStyle: TextStyle = AppTheme.typography.button
) {
    ActionButton(
        onClick,
        modifier,
        isEnabled,
        isLoading,
        border,
        shape,
        colors
    ) {
        Text(
            text = title,
            style = textStyle
        )
    }
}

@Composable
fun OutlinedActionButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    border: BorderStroke? = BorderStroke(
        width = 1.dp,
        color = AppTheme.colors.buttonPrimary
    ),
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        containerColor = AppTheme.colors.background,
        disabledContainerColor = AppTheme.colors.background,
        contentColor = AppTheme.colors.onSecondaryButton,
        disabledContentColor = AppTheme.colors.onSecondaryButtonDisabled,
    )
) {
    TextActionButton(
        title,
        onClick,
        modifier,
        enabled,
        isLoading,
        border,
        colors
    )
}

@Preview
@Composable
internal fun ActionButtonPreview() {
    AppTheme {
        TextActionButton(
            onClick = { },
            title = "Do some action"
        )
    }
}

@Preview
@Composable
internal fun ActionButtonPreview_Disabled() {
    AppTheme {
        TextActionButton(
            onClick = { },
            title = "Do some action",
            isEnabled = false
        )
    }
}

@Preview
@Composable
internal fun ActionButtonPreview_Loading() {
    AppTheme {
        TextActionButton(
            onClick = { },
            title = "Do some action",
            isLoading = true
        )
    }
}

@Preview
@Composable
private fun OutlinedActionButtonPreview() {
    AppTheme {
        OutlinedActionButton(
            title = "Do some action",
            onClick = { }
        )
    }
}