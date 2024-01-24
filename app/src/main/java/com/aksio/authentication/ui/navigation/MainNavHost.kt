package com.aksio.authentication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.aksio.core.common.state.SnackbarMessage
import com.aksio.features.authentication.navigation.navGraphAuthentication

@Composable
internal fun MainNavHost(
    startDestination: String,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    showMessage: (SnackbarMessage) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navGraphAuthentication(
            graphRoute = MainGraph.Authentication.route,
            showMessage = showMessage
        )
    }
}