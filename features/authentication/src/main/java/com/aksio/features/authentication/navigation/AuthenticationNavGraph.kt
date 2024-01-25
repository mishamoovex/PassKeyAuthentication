package com.aksio.features.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.aksio.core.common.state.SnackbarMessage
import com.aksio.features.authentication.ui.email.resigter.screen.EmailSignUpScreen
import com.aksio.features.authentication.ui.welcome.screen.WelcomeScreen

private sealed class AuthNavGraph(open val route: String) {
    data object Welcome : AuthNavGraph(route = "welcome")
    data object SingIn : AuthNavGraph(route = "singIn")
    data object SingUp : AuthNavGraph(route = "singUp")
}

fun NavGraphBuilder.navGraphAuthentication(
    graphRoute: String,
    showMessage: (SnackbarMessage) -> Unit
) {
    navigation(
        startDestination = AuthNavGraph.SingUp.route,
        route = graphRoute
    ) {
        composable(AuthNavGraph.Welcome.route) {
            WelcomeScreen()
        }
        composable(AuthNavGraph.SingUp.route) {
            EmailSignUpScreen()
        }
    }
}