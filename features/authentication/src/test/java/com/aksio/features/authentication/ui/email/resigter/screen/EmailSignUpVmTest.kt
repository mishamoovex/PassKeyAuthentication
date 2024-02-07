package com.aksio.features.authentication.ui.email.resigter.screen

import com.aksio.core.common.state.ValidationState
import com.aksio.core.tests_shared.MainDispatcherRule
import com.aksio.core.tests_shared.fake.common.FakeClock
import com.aksio.core.tests_shared.fake.common.FakeErrorMessenger
import com.aksio.core.tests_shared.fake.repository.FakeAuthenticationRepository
import com.aksio.core.tests_shared.util.setFlowCollector
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
        //Given a valid email
        val newEmail = "some@gmail.com"
        //When new email is set
        viewModel.uiState.value.emailState.onValueChanged(newEmail)
        //Than the email state value should then be updated to the new email
        viewModel.uiState.value.emailState.value.shouldBe(newEmail)
    }

    @Test
    fun `SHOULD set email validation state Pending WHEN email state created`() = runTest {
        //Given a fresh ViewModel
        //When verifying initial state
        //Than the validation state should be Pending
        viewModel.uiState.value.emailState.validationState.shouldBe(ValidationState.Pending)
    }

    @Test
    fun `SHOULD set email validation state Pending WHEN email length less than required`() =
        runTest {
            setFlowCollector(viewModel.uiState)
            //Given a string of length less then required
            val value = "1234"
            //When new value is set
            viewModel.uiState.value.emailState.onValueChanged(value)
            //Than the validation state should be Pending
            viewModel.uiState.value.emailState.validationState.shouldBe(ValidationState.Pending)
        }

    @Test
    fun `SHOULD set email validation state Invalid WHEN email length longer than required`() =
        runTest {
            setFlowCollector(viewModel.uiState)
            //Given a string of length longer then required
            val value = "1234567"
            //When new value is set
            emailValidationUseCase.isValid(isValid = false)
            viewModel.uiState.value.emailState.onValueChanged(value)
            //Than the validation state should be Invalid
            val expectedState = ValidationState.Invalid(emailValidationUseCase.errorMessage)
            viewModel.uiState.value.emailState.validationState.shouldBe(expectedState)
        }

    @Test
    fun `SHOULD set email validation state Invalid WHEN email length equals required length and not valid`() =
        runTest {
            setFlowCollector(viewModel.uiState)
            //Given an string that represents an email of required length
            val value = "123456"
            //When new value is set
            emailValidationUseCase.isValid(isValid = false)
            viewModel.uiState.value.emailState.onValueChanged(value)
            //Than the validation state should be Invalid
            val expectedState = ValidationState.Invalid(emailValidationUseCase.errorMessage)
            viewModel.uiState.value.emailState.validationState.shouldBe(expectedState)
        }

    @Test
    fun `SHOULD set email validation state Valid WHEN email is valid`() = runTest {
        setFlowCollector(viewModel.uiState)
        //Given a valid email
        val value = "some@gmail.com"
        //When new email is set
        viewModel.uiState.value.emailState.onValueChanged(value)
        //Than the validation state should be Valid
        viewModel.uiState.value.emailState.validationState.shouldBe(ValidationState.Valid)
    }

    //////////// Password state tests ///////////////

}