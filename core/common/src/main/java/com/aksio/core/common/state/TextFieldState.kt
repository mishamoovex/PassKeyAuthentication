package com.aksio.core.common.state

data class TextFieldState(
    val value: String = "",
    val errorMessage: MessageState? = null,
    val onValueChanged: (String) -> Unit
)