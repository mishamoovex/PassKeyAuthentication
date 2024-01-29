package com.aksio.features.authentication.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.aksio.core.common.state.TextMessage
import com.aksio.features.authentication.ui.email.resigter.screen.EmailSignUpScreen
import com.aksio.features.authentication.ui.email.resigter.screen.EmailSignUpVm
import com.aksio.features.authentication.ui.welcome.screen.WelcomeScreen

private sealed class AuthNavGraph(open val route: String) {
    data object Welcome : AuthNavGraph(route = "welcome")
    data object SingIn : AuthNavGraph(route = "singIn")
    data object SingUp : AuthNavGraph(route = "singUp")
}

fun NavGraphBuilder.navGraphAuthentication(
    navHostController: NavHostController,
    graphRoute: String,
    showMessage: (TextMessage) -> Unit
) {
    navigation(
        startDestination = AuthNavGraph.SingUp.route,
        route = graphRoute
    ) {
        composable(AuthNavGraph.Welcome.route) {
            WelcomeScreen()
        }

        composable(AuthNavGraph.SingUp.route) {

            val viewModel = hiltViewModel<EmailSignUpVm>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val messageState by viewModel.messageEvent.collectAsStateWithLifecycle()

            LaunchedEffect(messageState) {
                messageState.message?.let {
                    showMessage(it)
                    messageState.onShown()
                }
            }

            EmailSignUpScreen(
                uiState = uiState,
                navigateUp = navHostController::navigateUp
            )
        }
    }
}