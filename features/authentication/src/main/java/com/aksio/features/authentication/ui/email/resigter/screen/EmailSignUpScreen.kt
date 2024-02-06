package com.aksio.features.authentication.ui.email.resigter.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aksio.core.common.state.TextMessage
import com.aksio.core.designsystem.components.ActionableText
import com.aksio.core.designsystem.components.AppTextField
import com.aksio.core.designsystem.components.TextActionButton
import com.aksio.core.designsystem.theme.AppTheme
import com.aksio.features.authentication.R
import com.aksio.features.authentication.ui.email.resigter.state.EmailSignUpUiState

@Composable
internal fun EmailSignUpScreen(
    viewModel: EmailSignUpVm = hiltViewModel(),
    showMessage: (TextMessage) -> Unit,
    toEmailVerification: () -> Unit,
    toLogin: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val messages by viewModel.displayMessages.collectAsStateWithLifecycle()
    val navigationState by viewModel.navigationState.collectAsStateWithLifecycle()

    ScreenContent(
        uiState = uiState,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp),
        toLogin = toLogin
    )

    messages.firstOrNull()?.let { message ->
        LaunchedEffect(messages) {
            showMessage(message)
            viewModel.setMessageShown(message.id)
        }
    }

    navigationState.args?.let { navArgs ->
        LaunchedEffect(navArgs) {
            toEmailVerification()
            navigationState.onNavigated()
        }
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    uiState: EmailSignUpUiState,
    toLogin: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {

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
            isEnabled = uiState.actionButtonState.isEnabled
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        ActionableText(
            actionableText = R.string.email_sing_up_action_login,
            action = toLogin,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview
private fun ScreenPreview() {
    AppTheme {
        ScreenContent(
            uiState = EmailSignUpUiState(),
            toLogin = {}
        )
    }
}