package com.aksio.features.authentication.fake

import com.aksio.core.common.state.TextMessage
import com.aksio.features.authentication.domain.validation.StringValidationUseCase
import java.util.concurrent.atomic.AtomicBoolean

class FakeStringValidation(
    val errorMessage: TextMessage = TextMessage.StringMessage("validation error"),
    val isStringValid: Boolean = true
) : StringValidationUseCase {

    private val isValid = AtomicBoolean(isStringValid)

    override suspend fun invoke(value: String): TextMessage? =
        if (isValid.get()) null else errorMessage

    fun isValid(isValid: Boolean) {
        this.isValid.set(isValid)
    }
}