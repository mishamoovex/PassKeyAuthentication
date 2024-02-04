package com.aksio.core.tests_shared.fake.common

import com.aksio.core.common.core.messenger.error.ErrorMessenger
import com.aksio.core.common.state.TextMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class FakeErrorMessenger(
    val errorMessage: TextMessage = TextMessage.StringMessage("error")
) : ErrorMessenger {

    private val messageQueue = MutableStateFlow<List<TextMessage>>(emptyList())
    override val displayMessages: StateFlow<List<TextMessage>> = messageQueue

    override fun showError(throwable: Throwable) {
        messageQueue.update { currentMessages -> currentMessages + errorMessage }

    }

    override fun showMessage(message: TextMessage) {
        messageQueue.update { currentMessages -> currentMessages + message }
    }

    override fun setMessageShown(id: UUID) {
        messageQueue.update { currentMessages ->
            currentMessages.filterNot { it.id == id }
        }
    }
}