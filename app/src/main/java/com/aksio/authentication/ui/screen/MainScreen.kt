package com.aksio.authentication.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.aksio.authentication.ui.navigation.MainNavHost
import com.aksio.authentication.ui.screen.components.AppSnackbarHost
import com.aksio.authentication.ui.state.MainScreenUiState
import com.aksio.authentication.ui.state.rememberMainScreenState
import com.aksio.core.common.state.MessageState
import com.aksio.core.designsystem.theme.AppTheme

@Composable
fun MainScreen(
    uiState: MainScreenUiState,
    messageState: MessageState,
    hideSplashScreen: () -> Unit
) {

    val screenState = rememberMainScreenState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppTheme.colors.background,
        snackbarHost = {
            AppSnackbarHost(screenState.snackbarHostState)
        }
    ) { paddings ->
        if (uiState.route != null) {
            MainNavHost(
                startDestination = uiState.route,
                navHostController = screenState.navController,
                modifier = Modifier.padding(paddings),
                showMessage = screenState::showMessage,
            )
            LaunchedEffect(Unit) {
                hideSplashScreen()
            }
        }
    }

    LaunchedEffect(messageState){
        messageState.message?.let {
            screenState.showMessage(it)
            messageState.onShown
        }
    }
}