package com.aksio.authentication.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.aksio.core.common.state.TextMessage
import com.aksio.features.authentication.navigation.navGraphAuthentication
import com.aksio.features.home.navigation.navGraphHome

@Composable
internal fun MainNavHost(
    startDestination: String,
    emailVerificationRequired: Boolean,
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
            navHostController = navHostController,
            emailVerificationRequired = emailVerificationRequired
        )
        navGraphHome(
            graphRoute = MainGraph.Home.route
        )
    }
}

sealed class MainGraph(open val route: String) {

    data object Authentication : MainGraph(route = "graphAuthentication")

    data object Home : MainGraph(route = "graphHome")
}