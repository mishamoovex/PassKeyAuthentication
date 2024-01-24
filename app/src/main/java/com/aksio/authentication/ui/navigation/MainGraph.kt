package com.aksio.authentication.ui.navigation

sealed class MainGraph(open val route: String) {

    data object Authentication : MainGraph(route = "graphAuthentication")
}