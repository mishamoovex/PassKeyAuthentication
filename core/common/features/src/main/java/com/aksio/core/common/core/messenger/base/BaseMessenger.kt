package com.aksio.core.common.core.messenger.base

import com.aksio.core.common.state.TextMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

class BaseMessenger @Inject constructor() : Messenger {

    private val messageQueue = MutableStateFlow<List<TextMessage>>(emptyList())
    override val displayMessages: StateFlow<List<TextMessage>> = messageQueue

    override fun showMessage(message: TextMessage) {
        messageQueue.update { currentMessages -> currentMessages + message }
    }

    override fun setMessageShown(id: UUID) {
        messageQueue.update { currentMessages ->
            currentMessages.filterNot { it.id == id }
        }
    }
}