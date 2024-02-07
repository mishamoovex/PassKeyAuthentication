package com.aksio.core.common.state

data class NavigationState<T>(
    val args: T? = null,
    val onNavigated: () -> Unit = {}
)
