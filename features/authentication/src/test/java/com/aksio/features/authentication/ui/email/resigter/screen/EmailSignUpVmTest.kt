package com.aksio.features.authentication.ui.email.resigter.screen

import com.aksio.core.tests_shared.MainDispatcherRule
import com.aksio.core.tests_shared.fake.common.FakeClock
import com.aksio.core.tests_shared.fake.common.FakeErrorMessenger
import com.aksio.core.tests_shared.fake.repository.FakeAuthenticationRepository
import com.aksio.features.authentication.fake.FakeStringValidation
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
    fun `SHOULD set empty string as email value WHEN email state created`() {

    }

    @Test
    fun `SHOULD update email value value WHEN new value set`() {

    }

    @Test
    fun `SHOULD set email validation state Pending WHEN email state created`() {

    }

    @Test
    fun `SHOULD set email validation state Pending WHEN email length less than required`() {

    }

    @Test
    fun `SHOULD set email validation state Invalid WHEN email length longer than required`() {

    }

    @Test
    fun `SHOULD set email validation state Invalid WHEN email length equals required length and not valid`() {

    }

    @Test
    fun `SHOULD set email validation state Valid WHEN email is valid`() {

    }


}