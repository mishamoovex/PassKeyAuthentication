package com.aksio.authentication.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.aksio.core.common.state.TextMessage
import com.aksio.features.authentication.navigation.navGraphAuthentication

@Composable
internal fun MainNavHost(
    startDestination: String,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    showMessage: (TextMessage) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        navGraphAuthentication(
            graphRoute = MainGraph.Authentication.route,
            showMessage = showMessage,
            navHostController = navHostController
        )
    }
}