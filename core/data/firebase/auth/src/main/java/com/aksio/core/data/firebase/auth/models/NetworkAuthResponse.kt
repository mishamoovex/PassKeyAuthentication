package com.aksio.core.data.firebase.auth.models

import com.aksio.core.models.auth.AuthenticationProvider

data class NetworkAuthResponse(
    val email: String,
    val provider: AuthenticationProvider
)
