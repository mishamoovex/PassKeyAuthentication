package com.aksio.core.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aksio.core.designsystem.theme.AppTheme

@Composable
fun AppTextField(
    value: String,
    placeholder: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    supportingText: String? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        isError = isError,
        colors = TextFieldDefaults.colors(
            focusedPlaceholderColor = AppTheme.colors.textHintFocused,
            unfocusedPlaceholderColor = AppTheme.colors.textHilt,
            focusedContainerColor = AppTheme.colors.background,
            unfocusedContainerColor = AppTheme.colors.background,
            errorContainerColor = AppTheme.colors.background,
            focusedIndicatorColor = AppTheme.colors.textIndicatorFocused,
            unfocusedIndicatorColor = AppTheme.colors.textIndicator,
            errorIndicatorColor = AppTheme.colors.error,
            cursorColor = AppTheme.colors.buttonPrimary,
            errorCursorColor = AppTheme.colors.error,
            focusedSupportingTextColor = AppTheme.colors.textIndicatorFocused,
            unfocusedSupportingTextColor = AppTheme.colors.textIndicatorFocused,
            errorSupportingTextColor = AppTheme.colors.error
        ),
        textStyle = AppTheme.typography.bodyL,
        placeholder = {
            Text(
                text = placeholder,
                style = AppTheme.typography.bodyL.copy(
                    color = AppTheme.colors.textHilt
                )
            )
        },
        supportingText = {
            if (supportingText != null) {
                Text(
                    text = supportingText,
                    style = AppTheme.typography.bodyXs.copy(
                        color = AppTheme.colors.error
                    )
                )
            }
        }
    )
}

@Composable
@Preview
private fun AppTextPreview() {
    AppTheme {
        AppTextField(
            value = "misha@gmail.com",
            placeholder = "type somethings",
            onValueChanged = {}
        )
    }
}

@Composable
@Preview
private fun AppTextPreview_Placeholder() {
    AppTheme {
        AppTextField(
            value = "",
            placeholder = "type somethings",
            onValueChanged = {}
        )
    }
}

@Composable
@Preview
private fun AppTextPreview_Error() {
    AppTheme {
        AppTextField(
            value = "misha@gmail.com",
            placeholder = "type somethings",
            onValueChanged = {},
            isError = true,
            supportingText = "validation error"
        )
    }
}