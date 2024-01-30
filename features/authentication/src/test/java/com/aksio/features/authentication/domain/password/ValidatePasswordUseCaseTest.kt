package com.aksio.features.authentication.domain.password

import com.aksio.core.common.state.TextMessage
import com.aksio.features.authentication.R
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ValidatePasswordUseCaseTest {

    private companion object {
        const val PASSWORD_LENGTH = 6
    }

    private val useCase = ValidatePasswordUseCaseImpl()

    @Test
    fun `SHOULD return NULL WHEN password is valid`() = runTest {
        //Given a valid password
        val password = "1234qQ"
        //When validating given password
        val errorMessage = useCase(password, PASSWORD_LENGTH)
        //Than the error message must be null which means it is valid
        errorMessage.shouldBeNull()
    }

    @Test
    fun `SHOULD return EMPTY error WHEN password is empty`() = runTest {
        //Given an empty string that represents password
        val password = ""
        //When validating the given password
        val errorMessage = useCase(password, PASSWORD_LENGTH)
        //Than the result must be an "empty" error message
        errorMessage shouldBe TextMessage.ResourceMessage(
            templateRes = R.string.text_validation_error_password_empty
        )
    }

    @Test
    fun `SHOULD return a TOO_LONG error WHEN password is longer`() = runTest {
        //Given a password than longer than expected
        val password = "1234567"
        //When validating the given password
        val errorMessage = useCase(password, PASSWORD_LENGTH)
        //Than the result must be a "too long" error message
        errorMessage shouldBe TextMessage.ResourceMessage(
            templateRes = R.string.text_validation_error_password_too_long
        )
    }

    @Test
    fun `SHOULD return NO_DIGIT error WHEN contains no digits`() = runTest {
        //Given a password of the required length which doesn't contain digits
        val password = "Qwerty"
        //When validating the given password
        val errorMessage = useCase(password, PASSWORD_LENGTH)
        //Than the result must be a "no digits" error message
        errorMessage shouldBe TextMessage.BuildString(
            base = R.string.text_validation_error_password_base,
            cases = listOf(R.string.text_validation_error_password_base_digit)
        )
    }

    @Test
    fun `SHOULD return NO_LETTER error WHEN contains no letters`() = runTest {
        //Given a password of the required length which doesn't contain letters
        val password = "123456"
        //When validating the given password
        val errorMessage = useCase(password, PASSWORD_LENGTH)
        //Than the result must be a "no letters" error message
        errorMessage shouldBe TextMessage.BuildString(
            base = R.string.text_validation_error_password_base,
            cases = listOf(R.string.text_validation_error_password_base_letter)
        )
    }

    @Test
    fun `SHOULD return NO_CAPITALIZED_LETTER error WHEN contains no capital letters`() = runTest {
        //Given a password of the required length which doesn't contain capitalized letters
        val password = "12345q"
        //When validating the given password
        val errorMessage = useCase(password, PASSWORD_LENGTH)
        //Than the result must be a "no letters" error message
        errorMessage shouldBe TextMessage.BuildString(
            base = R.string.text_validation_error_password_base,
            cases = listOf(R.string.text_validation_error_password_base_capitalized_letter)
        )
    }

    @Test
    fun `SHOULD return NO_DIGITS and NO_LETTERS errors WHEN consists of special characters`() =
        runTest {
            //Given a password of the required length which consists of special characters
            val password = "++##%%"
            //When validating the given password
            val errorMessage = useCase(password, PASSWORD_LENGTH)
            //Than the result must be a "no letters" error message
            errorMessage shouldBe TextMessage.BuildString(
                base = R.string.text_validation_error_password_base,
                cases = listOf(
                    R.string.text_validation_error_password_base_digit,
                    R.string.text_validation_error_password_base_letter
                )
            )
        }

}