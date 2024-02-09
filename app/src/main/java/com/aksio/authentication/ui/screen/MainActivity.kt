package com.aksio.authentication.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
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
            AppTheme {
                MainScreen(
                    hideSplashScreen = { isSplashActive.set(false) }
                )
            }
        }
    }
}