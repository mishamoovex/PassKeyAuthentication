package com.aksio.features.authentication.ui.email.verify.screen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aksio.core.common.state.TextMessage
import com.aksio.core.designsystem.components.TextActionButton
import com.aksio.core.designsystem.theme.AppTheme
import com.aksio.features.authentication.R
import com.aksio.features.authentication.ui.email.verify.screen.composents.Counter
import com.aksio.features.authentication.ui.email.verify.state.EmailVerificationUiState

@Composable
internal fun EmailVerificationScreen(
    viewModel: EmailVerificationVm = hiltViewModel(),
    showMessage: (TextMessage) -> Unit,
    toLongin: () -> Unit
) {
    val messages by viewModel.displayMessages.collectAsStateWithLifecycle()

    ScreenContent(
        toLogin = toLongin,
        state = EmailVerificationUiState()
    )

    messages.firstOrNull()?.let { message ->
        LaunchedEffect(messages) {
            showMessage(message)
            viewModel.setMessageShown(message.id)
        }
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    toLogin: () -> Unit,
    state: EmailVerificationUiState
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.email_verification_header),
            style = AppTheme.typography.title,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.email_verification_description),
            style = AppTheme.typography.bodyS,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        TextActionButton(
            title = stringResource(R.string.email_verification_auth_btn),
            onClick = toLogin
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Counter(
            deadline = state.deadline,
            actionButtonState = state.actionButtonState
        )
    }
}

@Preview
@Composable
private fun ScreenContentPreview() {
    AppTheme {
        ScreenContent(
            toLogin = { },
            state = EmailVerificationUiState()
        )
    }
}