package com.aksio.features.authentication.domain.validation

import com.aksio.core.common.state.TextMessage

interface StringValidationUseCase {

    suspend operator fun invoke(value: String): TextMessage?

}