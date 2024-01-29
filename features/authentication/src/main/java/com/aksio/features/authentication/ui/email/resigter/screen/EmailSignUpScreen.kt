package com.aksio.features.authentication.ui.email.resigter.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aksio.core.designsystem.components.ActionableText
import com.aksio.core.designsystem.components.AppTextField
import com.aksio.core.designsystem.components.TextActionButton
import com.aksio.core.designsystem.components.Toolbar
import com.aksio.core.designsystem.theme.AppTheme
import com.aksio.features.authentication.R
import com.aksio.features.authentication.ui.email.resigter.state.EmailSignUpUiState

@Composable
internal fun EmailSignUpScreen(
    uiState: EmailSignUpUiState,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 24.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Toolbar(
            onBack = navigateUp
        )
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        Text(
            text = stringResource(R.string.email_sing_up_header),
            style = AppTheme.typography.title,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        AppTextField(
            value = uiState.emailState.value,
            placeholder = stringResource(R.string.email_sing_up_input_field_email_hint),
            onValueChanged = uiState.emailState.onValueChanged,
            supportingText = uiState.emailState.getErrorMessage(context),
            isError = uiState.emailState.isError()
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        AppTextField(
            value = uiState.passwordState.value,
            placeholder = stringResource(R.string.email_sing_up_input_filed_password_hilt),
            onValueChanged = uiState.passwordState.onValueChanged,
            isError = uiState.passwordState.isError(),
            supportingText = uiState.passwordState.getErrorMessage(context)
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        AppTextField(
            value = uiState.passwordConfirmationState.value,
            placeholder = stringResource(R.string.email_sing_up_input_filed_password_confirmation_hilt),
            onValueChanged = uiState.passwordConfirmationState.onValueChanged,
            isError = uiState.passwordConfirmationState.isError(),
            supportingText = uiState.passwordConfirmationState.getErrorMessage(context)
        )
        Spacer(
            modifier = Modifier.weight(weight = 1f)
        )
        TextActionButton(
            title = stringResource(R.string.email_sing_up_action_btn),
            onClick = uiState.actionButtonState.onClicked,
            isLoading = uiState.actionButtonState.isLoading,
            isEnabled = uiState.actionButtonState.isEnabled,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        ActionableText(
            text = stringResource(R.string.email_sing_up_action_login),
            action = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview
private fun ScreenPreview() {
    AppTheme {
        EmailSignUpScreen(
            uiState = EmailSignUpUiState(),
            navigateUp = {}
        )
    }
}