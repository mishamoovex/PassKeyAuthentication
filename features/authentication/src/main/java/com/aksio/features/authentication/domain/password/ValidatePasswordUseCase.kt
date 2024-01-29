package com.aksio.features.authentication.domain.password

import com.aksio.core.common.state.TextMessage

interface ValidatePasswordUseCase {

    suspend operator fun invoke(
        password: String,
        length: Int
    ): TextMessage?
}