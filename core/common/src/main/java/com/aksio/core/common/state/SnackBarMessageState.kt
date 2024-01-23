package com.aksio.core.common.state

import androidx.annotation.StringRes
import java.util.UUID

sealed class SnackbarMessage(
    val id: UUID = UUID.randomUUID()
) {
    data class ResourceMessage(
        @StringRes val templateRes: Int,
        val placeholders: List<String> = emptyList(),
    ) : SnackbarMessage()

    data class StringMessage(
        val value: String
    ) : SnackbarMessage()
}
