package com.aksio.authentication.ui.screen.components

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aksio.core.designsystem.theme.AppTheme

@Composable
fun AppSnackbarHost(hostState: SnackbarHostState) {
    SnackbarHost(hostState) { snackbarData ->
        Snackbar(
            snackbarData = snackbarData,
            modifier = Modifier.systemBarsPadding(),
            containerColor = AppTheme.colors.buttonDisabled,
            contentColor = AppTheme.colors.onSecondaryButton,
        )
    }
}