package com.aksio.core.common.state

data class MessageState(
    val message: TextMessage? = null,
    val onShown: () -> Unit
)
