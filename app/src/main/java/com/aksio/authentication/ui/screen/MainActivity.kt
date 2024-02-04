package com.aksio.authentication.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aksio.core.designsystem.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.atomic.AtomicBoolean

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val isSplashActive = AtomicBoolean(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { isSplashActive.get() }

        setContent {
            val viewModel = hiltViewModel<MainScreenVm>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val messageState by viewModel.displayMessages.collectAsStateWithLifecycle()

            AppTheme {
                MainScreen(
                    uiState = uiState,
                    messages = messageState,
                    onMessageShown = viewModel::setMessageShown,
                    hideSplashScreen = { isSplashActive.set(false) }
                )
            }
        }
    }
}