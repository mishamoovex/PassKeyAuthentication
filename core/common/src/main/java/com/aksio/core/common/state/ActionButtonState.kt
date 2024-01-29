package com.aksio.core.common.state

data class ActionButtonState(
    val isLoading: Boolean = false,
    val isEnabled: Boolean = false,
    val onClicked: () -> Unit = {}
)
