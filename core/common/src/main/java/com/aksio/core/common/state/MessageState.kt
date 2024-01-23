package com.aksio.core.common.state

data class MessageState(
    val message: SnackbarMessage? = null,
    val onShown: () -> Unit
)
