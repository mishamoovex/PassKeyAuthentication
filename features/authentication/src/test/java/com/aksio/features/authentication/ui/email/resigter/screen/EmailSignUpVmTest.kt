package com.aksio.features.authentication.ui.email.resigter.screen

import com.aksio.core.common.state.TextMessage
import com.aksio.core.common.state.ValidationState
import com.aksio.core.tests_shared.MainDispatcherRule
import com.aksio.core.tests_shared.fake.common.FakeClock
import com.aksio.core.tests_shared.fake.common.FakeErrorMessenger
import com.aksio.core.tests_shared.fake.repository.FakeAuthenticationRepository
import com.aksio.core.tests_shared.util.setFlowCollector
import com.aksio.features.authentication.R
import com.aksio.features.authentication.fake.FakeStringValidation
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class EmailSignUpVmTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val clock = FakeClock()
    private lateinit var messenger: FakeErrorMessenger
    private lateinit var emailValidationUseCase: FakeStringValidation
    private lateinit var passwordValidationUseCase: FakeStringValidation
    private lateinit var authenticationRepository: FakeAuthenticationRepository
    private lateinit var viewModel: EmailSignUpVm

    @Before
    fun setUp() {
        messenger = FakeErrorMessenger()
        emailValidationUseCase = FakeStringValidation()
        passwordValidationUseCase = FakeStringValidation()
        authenticationRepository = FakeAuthenticationRepository(clock)
        viewModel = EmailSignUpVm(
            validateEmailUseCase = emailValidationUseCase,
            validatePasswordUseCase = passwordValidationUseCase,
            messenger = messenger,
            authenticationRepository = authenticationRepository
        )
    }

    //////////// Email state tests ///////////////

    @Test
    fun `SHOULD set empty string as email value WHEN email state created`() = runTest {
        //Given a fresh ViewModel
        //When verifying initial state
        //Than the email state text value should be an empty string
        viewModel.uiState.value.emailState.value.shouldBeEmpty()
    }

    @Test
    fun `SHOULD update email value WHEN new value of required length is set`() = runTest {
        setFlowCollector(viewModel.uiState)
        //Given an email of required length
        val newEmail = "some@gmail.com"
        //When a new email is set
        viewModel.uiState.value.emailState.onValueChanged(newEmail)
        //Than the email state value should then be updated
        viewModel.uiState.value.emailState.value.shouldBe(newEmail)
    }

    @Test
    fun `SHOULD set email validation state Pending WHEN email state created`() = runTest {
        //Given a fresh ViewModel
        //When verifying initial state
        //Than the email validation state should be Pending
        viewModel.uiState.value.emailState.validationState.shouldBe(ValidationState.Pending)
    }

    @Test
    fun `SHOULD set email validation state Pending WHEN email length less than required`() =
        runTest {
            setFlowCollector(viewModel.uiState)
            //Given a string of length less then required
            val value = "1234"
            //When a new value is set
            viewModel.uiState.value.emailState.onValueChanged(value)
            //Than the email validation state should be Pending
            viewModel.uiState.value.emailState.validationState.shouldBe(ValidationState.Pending)
        }

    @Test
    fun `SHOULD set email validation state Invalid WHEN email is not valid`() =
        runTest {
            setFlowCollector(viewModel.uiState)
            //Given an invalid email
            val value = "emailcom"
            //When a new value is set
            emailValidationUseCase.isValid(isValid = false)
            viewModel.uiState.value.emailState.onValueChanged(value)
            //Than the email validation state should be Invalid
            val expectedState = ValidationState.Invalid(emailValidationUseCase.errorMessage)
            viewModel.uiState.value.emailState.validationState.shouldBe(expectedState)
        }

    @Test
    fun `SHOULD set email validation state Valid WHEN email is valid`() = runTest {
        setFlowCollector(viewModel.uiState)
        //Given a valid email
        val value = "some@gmail.com"
        //When a new email is set
        viewModel.uiState.value.emailState.onValueChanged(value)
        //Than the email validation state should be Valid
        viewModel.uiState.value.emailState.validationState.shouldBe(ValidationState.Valid)
    }

    //////////// Password state tests ///////////////

    @Test
    fun `SHOULD set empty string as password WHEN password state created`() = runTest {
        //Given a fresh ViewModel
        //When verifying initial state
        //Than the password state text value should be an empty string
        viewModel.uiState.value.passwordState.value.shouldBeEmpty()
    }

    @Test
    fun `SHOULD update password value WHEN new value of required length is set`() = runTest {
        setFlowCollector(viewModel.uiState)
        //Given a password of required length
        val newPassword = "123456"
        //When a new password is set
        viewModel.uiState.value.passwordState.onValueChanged(newPassword)
        //Than the password state value should then be updated
        viewModel.uiState.value.passwordState.value.shouldBe(newPassword)
    }

    @Test
    fun `SHOULD set password validation state Pending WHEN password state created`() = runTest {
        //Given a fresh ViewModel
        //When verifying initial state
        //Than the password validation state should be Pending
        viewModel.uiState.value.passwordState.validationState.shouldBe(ValidationState.Pending)
    }

    @Test
    fun `SHOULD set password validation state Pending WHEN password length less than required`() =
        runTest {
            setFlowCollector(viewModel.uiState)
            //Given a string of length less then required
            val value = "1234"
            //When a new value is set
            viewModel.uiState.value.passwordState.onValueChanged(value)
            //Than the password validation state should be Pending
            viewModel.uiState.value.passwordState.validationState.shouldBe(ValidationState.Pending)
        }

    @Test
    fun `SHOULD set password validation state Invalid WHEN password is not valid`() =
        runTest {
            setFlowCollector(viewModel.uiState)
            //Given an invalid password
            val value = "1234567"
            //When a new value is set
            passwordValidationUseCase.isValid(isValid = false)
            viewModel.uiState.value.passwordState.onValueChanged(value)
            //Than the password validation state should be Invalid
            val expectedState = ValidationState.Invalid(passwordValidationUseCase.errorMessage)
            viewModel.uiState.value.passwordState.validationState.shouldBe(expectedState)
        }

    @Test
    fun `SHOULD set password validation state Valid WHEN password is valid`() = runTest {
        setFlowCollector(viewModel.uiState)
        //Given a valid password
        val value = "1234qQ"
        //When a new value is set
        viewModel.uiState.value.passwordState.onValueChanged(value)
        //Than the password validation state should be Valid
        viewModel.uiState.value.passwordState.validationState.shouldBe(ValidationState.Valid)
    }

    //////////// Confirmation password state tests ///////////////

    @Test
    fun `SHOULD set empty string as confirmation password WHEN confirmation password state created`() =
        runTest {
            //Given a fresh ViewModel
            //When verifying initial state
            //Than the confirmation password state text value should be an empty string
            viewModel.uiState.value.passwordConfirmationState.value.shouldBeEmpty()
        }

    @Test
    fun `SHOULD set confirmation password validation state Pending WHEN confirmation password state created`() =
        runTest {
            //Given a fresh ViewModel
            //When verifying initial state
            //Than the confirmation password validation state should be Pending
            viewModel.uiState.value.passwordConfirmationState.validationState.shouldBe(ValidationState.Pending)
        }

    @Test
    fun `SHOULD set confirmation password validation state Pending WHEN confirmationPassword length and password length are not equal`() =
        runTest {
            setFlowCollector(viewModel.uiState)
            //Given a password and confirmation password that have a different length
            val password = "1234567"
            val confirmationPassword = "123"
            //When passwords are set
            viewModel.uiState.value.passwordState.onValueChanged(password)
            viewModel.uiState.value.passwordConfirmationState.onValueChanged(confirmationPassword)
            //Than the confirmation password validation state should be Pending
            viewModel.uiState.value.passwordConfirmationState.validationState.shouldBe(ValidationState.Pending)
        }

    @Test
    fun `SHOULD set confirmation password validation state Invalid WHEN confirmation password and password are not equal`() =
        runTest {
            setFlowCollector(viewModel.uiState)
            //Given a password and a confirmation password that have the same length
            //but aren't equal
            val password = "123456"
            val confirmationPassword = "12345Q"
            //When passwords are set
            viewModel.uiState.value.passwordState.onValueChanged(password)
            viewModel.uiState.value.passwordConfirmationState.onValueChanged(confirmationPassword)
            //Than the confirmation password validation state should be Invalid
            val state = ValidationState.Invalid(
                errorMessage = TextMessage.ResourceMessage(
                    templateRes = R.string.text_validation_error_password_confirmation
                )
            )
            viewModel.uiState.value.passwordConfirmationState.validationState.shouldBe(state)
        }

    @Test
    fun `SHOULD set confirmation password validation state Valid WHEN confirmation password and password are equal and valid`() =
        runTest {
            setFlowCollector(viewModel.uiState)
            //Given a password and confirmation password that are equal and valid
            val password = "1234qQ"
            val confirmationPassword = "1234qQ"
            //When passwords are set
            viewModel.uiState.value.passwordState.onValueChanged(password)
            viewModel.uiState.value.passwordConfirmationState.onValueChanged(confirmationPassword)
            //Than the confirmation password validation state should be Valid
            viewModel.uiState.value.passwordConfirmationState.validationState.shouldBe(ValidationState.Valid)
        }
}