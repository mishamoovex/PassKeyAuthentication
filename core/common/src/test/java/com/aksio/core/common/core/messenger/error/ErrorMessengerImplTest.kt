package com.aksio.core.common.core.messenger.error

import com.aksio.core.common.core.messenger.base.BaseMessenger
import com.aksio.core.common.state.TextMessage
import io.kotest.matchers.collections.shouldContain
import org.junit.Before
import org.junit.Test

class ErrorMessengerImplTest {

    private lateinit var messenger: ErrorMessengerImpl

    @Before
    fun setUp() {
        messenger = ErrorMessengerImpl(BaseMessenger())
    }

    @Test
    fun `SHOULD return message with rationale WHEN error contains rationale`() {
        //Given a Throwable that contains a rationale
        val rationale = "rationale"
        val throwable = Throwable(message = rationale)
        //When showing an error message
        messenger.showError(throwable)
        //Than display messages should contain the rationale
        val expectedMessage = TextMessage.StringMessage(rationale)
        messenger.displayMessages.value.shouldContain(expectedMessage)
    }

    @Test
    fun `SHOULD return message with default error WHEN error does not contains rationale`() {
        //Given a Throwable that doesn't contains a rationale
        val throwable = Throwable()
        //When showing an error message
        messenger.showError(throwable)
        //Than display messages should contain a message with default error
        val expected = TextMessage.StringMessage("Unknown error")
        messenger.displayMessages.value.shouldContain(expected)
    }

}