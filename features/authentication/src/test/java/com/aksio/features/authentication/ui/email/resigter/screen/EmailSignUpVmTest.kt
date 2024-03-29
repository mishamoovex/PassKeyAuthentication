package com.aksio.features.authentication.ui.email.resigter.screen

import com.aksio.core.common.state.TextMessage
import com.aksio.core.common.state.ValidationState
import com.aksio.core.tests_shared.MainDispatcherRule
import com.aksio.core.tests_shared.fake.common.FakeClock
import com.aksio.core.tests_shared.fake.common.FakeErrorMessenger
import com.aksio.core.tests_shared.fake.repository.FakeAuthenticationRepository
import com.aksio.core.tests_shared.util.collectFromFlow
import com.aksio.core.tests_shared.util.setFlowCollector
import com.aksio.features.authentication.R
import com.aksio.features.authentication.fake.FakeStringValidation
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainOnly
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeEmpty
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class EmailSignUpVmTest {

    private companion object {
        const val validEmail = "some@gmail.com"
        const val validPassword = "1234qQ"
    }

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
        val newEmail = validEmail
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
        //When a new email is set
        viewModel.uiState.value.emailState.onValueChanged(validEmail)
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
        val newPassword = validPassword
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
        //When a new value is set
        viewModel.uiState.value.passwordState.onValueChanged(validPassword)
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
            val password = validPassword
            val confirmationPassword = validPassword
            //When passwords are set
            viewModel.uiState.value.passwordState.onValueChanged(password)
            viewModel.uiState.value.passwordConfirmationState.onValueChanged(confirmationPassword)
            //Than the confirmation password validation state should be Valid
            viewModel.uiState.value.passwordConfirmationState.validationState.shouldBe(ValidationState.Valid)
        }

    //////////// Action button state tests ///////////////

    @Test
    fun `SHOULD set isLoading = False WHEN action button state created`() = runTest {
        //Given a fresh ViewModel
        //When verifying an initial state
        //Than isLoading should be False
        viewModel.uiState.value.actionButtonState.isLoading.shouldBeFalse()
    }

    @Test
    fun `SHOULD set isLoading = True WHEN signUp() request launched`() = runTest {
        val states = collectFromFlow(viewModel.uiState)
        //Given an initial loading state
        //When the sign up request is started
        viewModel.uiState.value.actionButtonState.onClicked()
        //Than the loading state should be changed
        states.any { it.actionButtonState.isLoading }.shouldBeTrue()
    }

    @Test
    fun `SHOULD set isEnabled = False WHEN action button state created`() = runTest {
        //Given a fresh ViewModel
        //When verifying an initial state
        //Than isEnabled should be False
        viewModel.uiState.value.actionButtonState.isEnabled.shouldBeFalse()
    }

    @Test
    fun `SHOULD set isEnabled = True WHEN required input data are Valid`() = runTest {
        setFlowCollector(viewModel.uiState)
        //Given a valid user input
        //When all inputs are set
        setValidUserInput()
        //Than the action button isEnabled state should be True
        viewModel.uiState.value.actionButtonState.isEnabled.shouldBeTrue()
    }

    //////////// Register user tests ///////////////

    @Test
    fun `SHOULD register a new user WHEN signUp() request launched`() = runTest {
        //Given a valid user input
        setValidUserInput()
        //When the sign up request launched
        viewModel.uiState.value.actionButtonState.onClicked()
        //Then a new user should be created
        authenticationRepository.isAuthenticated().shouldBeTrue()
    }

    @Test
    fun `SHOULD send a verification email WHEN user authorized`() = runTest {
        //Given a valid user input
        setValidUserInput()
        //When a user authorized
        viewModel.uiState.value.actionButtonState.onClicked()
        //Then a verification email should be sent
        authenticationRepository.isEmailVerificationSent().shouldBeTrue()
    }

    @Test
    fun `SHOULD set a display error WHEN signUp() request failed`() {
        //Given a valid user input
        setValidUserInput()
        //When the sign up request failed
        authenticationRepository.shouldThrowException(isFailedRequest = true)
        viewModel.uiState.value.actionButtonState.onClicked()
        //Then a display error should be set
        messenger.displayMessages.value.shouldContainOnly(messenger.errorMessage)
    }

    //////////// Navigation state tests ///////////////

    @Test
    fun `SHOULD set navigation args WHEN signUp() request completed`() {
        //Given a valid user input
        setValidUserInput()
        //When the sign up request completed
        viewModel.uiState.value.actionButtonState.onClicked()
        //Then a navigation args should be set
        viewModel.navigationState.value.args.shouldNotBeNull()
    }

    @Test
    fun `SHOUlD clean up navigation args WHEN navigated`() {
        //Given an valid user input
        setValidUserInput()
        viewModel.uiState.value.actionButtonState.onClicked()
        //When onNavigated callback invoked
        viewModel.navigationState.value.onNavigated()
        //Then the navigation args should be removed
        viewModel.navigationState.value.args.shouldBeNull()
    }

    private fun setValidUserInput() {
        viewModel.uiState.value.emailState.onValueChanged(validEmail)
        viewModel.uiState.value.passwordState.onValueChanged(validPassword)
        viewModel.uiState.value.passwordConfirmationState.onValueChanged(validPassword)
    }

}