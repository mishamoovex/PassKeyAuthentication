package com.aksio.features.authentication.ui.email.resigter.screen

import com.aksio.core.tests_shared.MainDispatcherRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class EmailSignUpVmTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: EmailSignUpVm

    @Before
    fun setUp(){

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