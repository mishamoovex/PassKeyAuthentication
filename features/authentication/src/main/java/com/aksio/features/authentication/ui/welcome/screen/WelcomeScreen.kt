package com.aksio.features.authentication.ui.welcome.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aksio.core.designsystem.components.AppTextField
import com.aksio.core.designsystem.components.OutlinedActionButton
import com.aksio.core.designsystem.components.TextActionButton
import com.aksio.core.designsystem.theme.AppTheme
import com.aksio.features.authentication.R

@Composable
internal fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 80.dp,
                bottom = 24.dp,
                start = 16.dp,
                end = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.welcome_screen_header),
            style = AppTheme.typography.title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        AppTextField(
            value = "email",
            placeholder = "type email",
            onValueChanged = {}
        )
        Spacer(
            modifier = Modifier.weight(weight = 1f)
        )
        TextActionButton(
            title = stringResource(R.string.welcome_screen_main_action_title),
            onClick = { /*TODO*/ },
        )
        OutlinedActionButton(
            title = stringResource(R.string.welcome_screen_secondary_action_title),
            onClick = { /*TODO*/ },
        )
        PrivacyText(
            termsLink = "",
            policyLink = "",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
@Preview
private fun WelcomeScreenPreview() {
    AppTheme {
        WelcomeScreen()
    }
}