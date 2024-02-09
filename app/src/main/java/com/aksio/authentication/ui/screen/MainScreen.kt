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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aksio.authentication.ui.navigation.MainNavHost
import com.aksio.authentication.ui.screen.components.AppSnackbarHost
import com.aksio.authentication.ui.state.rememberMainScreenState
import com.aksio.core.designsystem.components.Toolbar
import com.aksio.core.designsystem.theme.AppTheme

@Composable
fun MainScreen(
    viewModel: MainScreenVm = hiltViewModel(),
    hideSplashScreen: () -> Unit
) {
    val screenState = rememberMainScreenState()
    val isBackEnabled by screenState.isTopBarNavigationEnabled.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val messages by viewModel.displayMessages.collectAsStateWithLifecycle()

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

        uiState.route?.let { startDestination ->
            MainNavHost(
                startDestination = startDestination,
                navHostController = screenState.navController,
                modifier = Modifier
                    .padding(paddings)
                    .padding(
                        top = 32.dp,
                        bottom = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                showMessage = screenState::showMessage,
                emailVerificationRequired = uiState.emailVerificationRequired
            )
            LaunchedEffect(Unit) {
                hideSplashScreen()
            }
        }
    }

    messages.firstOrNull()?.let { displayMessage ->
        LaunchedEffect(messages) {
            screenState.showMessage(displayMessage)
            viewModel.setMessageShown(displayMessage.id)
        }
    }
}