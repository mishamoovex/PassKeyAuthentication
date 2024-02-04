package com.aksio.features.authentication.domain.email

import com.aksio.core.common.state.TextMessage
import com.aksio.features.authentication.R
import com.aksio.features.authentication.domain.validation.ValidateEmailUseCase
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ValidateEmailUseCaseTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val useCase = ValidateEmailUseCase(testDispatcher)

    @Test
    fun `SHOULD return null WHEN email is valid`() = runTest(testDispatcher) {
        val result = useCase.invoke("email@bank.com")
        result.shouldBeNull()
    }

    @Test
    fun `SHOULD return an empty error WHEN email is empty`() = runTest(testDispatcher) {
        val result = useCase.invoke("")
        result shouldBe TextMessage.ResourceMessage(R.string.text_validation_error_empty_email)
    }

    @Test
    fun `SHOULD return an invalid error WHEN domain separator is missed`() =
        runTest(testDispatcher) {
            val result = useCase.invoke("email.com")
            result shouldBe TextMessage.ResourceMessage(R.string.text_validation_error_invalid_email)
        }

    @Test
    fun `SHOULD return an invalid error WHEN sub domain separator is missed`() =
        runTest(testDispatcher) {
            val result = useCase.invoke("email@com")
            result shouldBe TextMessage.ResourceMessage(R.string.text_validation_error_invalid_email)
        }

    @Test
    fun `SHOULD return an invalid error WHEN email don't have domain separators`() =
        runTest(testDispatcher) {
            val result = useCase.invoke("email")
            result shouldBe TextMessage.ResourceMessage(R.string.text_validation_error_invalid_email)
        }

    @Test
    fun `SHOULD return an invalid error WHEN email contains special characters`() =
        runTest(testDispatcher) {
            val result = useCase.invoke("##$%@gmail.com")
            result shouldBe TextMessage.ResourceMessage(R.string.text_validation_error_invalid_email)
        }
}