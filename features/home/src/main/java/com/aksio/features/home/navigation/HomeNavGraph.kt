package com.aksio.features.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aksio.features.home.screen.HomeScreen

fun NavGraphBuilder.navGraphHome(
    graphRoute: String
) {
    composable(graphRoute) {
        HomeScreen()
    }
}