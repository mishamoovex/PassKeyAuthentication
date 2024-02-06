package com.aksio.features.authentication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.aksio.core.common.state.TextMessage
import com.aksio.features.authentication.ui.email.login.screen.EmailSingInScreen
import com.aksio.features.authentication.ui.email.resigter.screen.EmailSignUpScreen
import com.aksio.features.authentication.ui.welcome.screen.WelcomeScreen

private sealed class AuthNavGraph(open val route: String) {
    data object Welcome : AuthNavGraph(route = "Welcome")
    data object EmailSignIn : AuthNavGraph(route = "EmailSingIn")
    data object EmailSignUp : AuthNavGraph(route = "EmailSingUp")
}

fun NavGraphBuilder.navGraphAuthentication(
    navHostController: NavHostController,
    graphRoute: String,
    showMessage: (TextMessage) -> Unit
) {
    navigation(
        startDestination = AuthNavGraph.EmailSignUp.route,
        route = graphRoute
    ) {
        composable(AuthNavGraph.Welcome.route) {
            WelcomeScreen()
        }

        composable(AuthNavGraph.EmailSignUp.route) {
            EmailSignUpScreen(
                showMessage = showMessage,
                toEmailVerification = {},
                toLogin = {
                    navHostController.navigate(AuthNavGraph.EmailSignIn.route) {
                        popUpTo(AuthNavGraph.EmailSignUp.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AuthNavGraph.EmailSignIn.route) {
            EmailSingInScreen()
        }
    }
}