package com.aksio.core.common.core.messenger.base

import com.aksio.core.common.state.TextMessage
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

/**
 * Responsible for managing the message queue.
 *
 * Typical use cases:
 *    1. Display a message from a VM based on some events completion
 *    2. Display a message from a Fragment as a response on some clicks
 */
interface Messenger {

    /**
     * Queue of messages to display.
     */
    val displayMessages: StateFlow<List<TextMessage>>

    /**
     * Schedules a display message.
     *
     * @param message that should be displayed
     */
    fun showMessage(message: TextMessage)

    /**
     * Removes a message with a given [id] from the queue.
     *
     * @param id of a message to remove
     */
    fun setMessageShown(id: UUID)
}