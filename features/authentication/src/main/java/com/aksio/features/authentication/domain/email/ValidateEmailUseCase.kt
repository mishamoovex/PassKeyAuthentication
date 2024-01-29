package com.aksio.features.authentication.domain.email

import com.aksio.core.common.state.TextMessage

interface ValidateEmailUseCase {

    suspend operator fun invoke(email: String): TextMessage?
}