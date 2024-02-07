package com.aksio.core.models.auth

sealed class RegistrationRequest {

    data class Password(
        val email: String,
        val password: String
    ) : RegistrationRequest()


    data class Google(
        val token: String
    ) : RegistrationRequest()
}