package com.aksio.features.authentication.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.aksio.core.common.state.SnackbarMessage

private sealed class AuthNavGraph(open val route: String) {
    data object SingIn : AuthNavGraph(route = "singIn")
    data object SingUp : AuthNavGraph(route = "singUp")
}

fun NavGraphBuilder.navGraphAuthentication(
    graphRoute: String,
    showMessage: (SnackbarMessage) -> Unit
) {
    navigation(
        startDestination = AuthNavGraph.SingIn.route,
        route = graphRoute
    ) {
        composable(AuthNavGraph.SingIn.route) {

        }
    }
}