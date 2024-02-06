package com.aksio.authentication.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aksio.authentication.ui.navigation.MainNavHost
import com.aksio.authentication.ui.screen.components.AppSnackbarHost
import com.aksio.authentication.ui.state.MainScreenUiState
import com.aksio.authentication.ui.state.rememberMainScreenState
import com.aksio.core.common.state.TextMessage
import com.aksio.core.designsystem.components.Toolbar
import com.aksio.core.designsystem.theme.AppTheme
import java.util.UUID

@Composable
fun MainScreen(
    uiState: MainScreenUiState,
    messages: List<TextMessage>,
    onMessageShown: (UUID) -> Unit,
    hideSplashScreen: () -> Unit
) {

    val screenState = rememberMainScreenState()
    val isBackEnabled by screenState.isTopBarNavigationEnabled.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppTheme.colors.background,
        snackbarHost = {
            AppSnackbarHost(screenState.snackbarHostState)
        },
        topBar = {
            Toolbar(
                onBack = screenState.navController::navigateUp,
                modifier = Modifier.statusBarsPadding(),
                isBackNavigationEnabled = isBackEnabled
            )
        }
    ) { paddings ->
        if (uiState.route != null) {
            MainNavHost(
                startDestination = uiState.route,
                navHostController = screenState.navController,
                modifier = Modifier
                    .padding(paddings)
                    .padding(
                        bottom = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                showMessage = screenState::showMessage,
            )
            LaunchedEffect(Unit) {
                hideSplashScreen()
            }
        }
    }

    LaunchedEffect(messages) {
        messages.firstOrNull()?.let { displayMessage ->
            screenState.showMessage(displayMessage)
            onMessageShown(displayMessage.id)
        }
    }
}