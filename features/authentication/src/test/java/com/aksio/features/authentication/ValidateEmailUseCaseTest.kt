package com.aksio.features.authentication

import com.aksio.core.common.state.TextMessage
import com.aksio.features.authentication.domain.email.ValidateEmailUseCaseImpl
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ValidateEmailUseCaseTest {

    private val useCase = ValidateEmailUseCaseImpl()

    @Test
    fun `SHOULD return null WHEN email is valid`() = runTest {
        val result = useCase.invoke("email@bank.com")
        result.shouldBeNull()
    }

    @Test
    fun `SHOULD return an empty error WHEN email is empty`() = runTest {
        val result = useCase.invoke("")
        result shouldBe TextMessage.ResourceMessage(R.string.text_validation_error_empty_email)
    }

    @Test
    fun `SHOULD return an invalid error WHEN domain separator is missed`() = runTest {
        val result = useCase.invoke("email.com")
        result shouldBe TextMessage.ResourceMessage(R.string.text_validation_error_invalid_email)
    }

    @Test
    fun `SHOULD return an invalid error WHEN sub domain separator is missed`() = runTest {
        val result = useCase.invoke("email@com")
        result shouldBe TextMessage.ResourceMessage(R.string.text_validation_error_invalid_email)
    }

    @Test
    fun `SHOULD return an invalid error WHEN email don't have domain separators`() = runTest {
        val result = useCase.invoke("email")
        result shouldBe TextMessage.ResourceMessage(R.string.text_validation_error_invalid_email)
    }

    @Test
    fun `SHOULD return an invalid error WHEN email contains special characters`() = runTest {
        val result = useCase.invoke("##$%@gmail.com")
        result shouldBe TextMessage.ResourceMessage(R.string.text_validation_error_invalid_email)
    }
}