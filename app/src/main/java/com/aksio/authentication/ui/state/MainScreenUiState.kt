package com.aksio.authentication.ui.state

data class MainScreenUiState(
    val route: String? = null,
    val emailVerificationRequired: Boolean = false
)
