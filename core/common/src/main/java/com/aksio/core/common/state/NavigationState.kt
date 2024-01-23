package com.aksio.core.common.state

data class NavigationState<T>(
    val event: T? = null,
    val onNavigated: () -> Unit
)
