package com.aksio.core.common.core.messenger.base

import com.aksio.core.common.state.TextMessage
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import org.junit.Before
import org.junit.Test

class BaseMessengerTest {

    private lateinit var messenger: BaseMessenger

    @Before
    fun setUp() {
        messenger = BaseMessenger()
    }

    @Test
    fun `SHOULD return empty list WHEN no messages submitted`() {
        //Given the default state of Messenger
        //When no messages submitted
        //Then display messages should be empty
        messenger.displayMessages.value.shouldBeEmpty()
    }

    @Test
    fun `SHOULD return message WHEN message submitted`() {
        //Given the default state of Messenger and a message to display
        val message = TextMessage.StringMessage("")
        //When the message is submitted
        messenger.showMessage(message)
        //Then the display messages should contain the submitted message
        messenger.displayMessages.value.shouldContain(message)
    }

    @Test
    fun `SHOULD return multiple messages WHEN when multiple messaged submitted`() {
        //Given the default state of Messenger and messages to display
        val messages = listOf(
            TextMessage.StringMessage(""),
            TextMessage.StringMessage("")
        )
        //When messages is submitted
        messages.onEach(messenger::showMessage)
        //Then the display messages should contain all submitted messages
        messenger.displayMessages.value.shouldContainAll(messages)
    }

    @Test
    fun `SHOULD return empty list WHEN message set shown`() {
        //Given the Messenger with a message to display
        val message = TextMessage.StringMessage("")
        messenger.showMessage(message)
        //When the message set as shown
        messenger.setMessageShown(message.id)
        //Then the display messages should be empty
        messenger.displayMessages.value.shouldBeEmpty()
    }

    @Test
    fun `SHOULD return empty list WHEN multiple messages submitted and set shown`() {
        //Given the Messenger with multiple messages to display
        val messages = listOf(
            TextMessage.StringMessage(""),
            TextMessage.StringMessage("")
        )
        messages.onEach(messenger::showMessage)
        //When the messages set as shown
        messages.onEach { messenger.setMessageShown(it.id) }
        //Then the display messages should be empty
        messenger.displayMessages.value.shouldBeEmpty()
    }

    @Test
    fun `SHOULD return messages WHEN multiple messaged submitted and partially set shown`() {
        //Given the Messenger with multiple messages to display
        val messages = listOf(
            TextMessage.StringMessage(""),
            TextMessage.StringMessage("")
        )
        messages.onEach(messenger::showMessage)
        //When one message set as shown
        messenger.setMessageShown(messages.first().id)
        //Then one message should be left
        messenger.displayMessages.value.shouldContain(messages.last())
    }


}