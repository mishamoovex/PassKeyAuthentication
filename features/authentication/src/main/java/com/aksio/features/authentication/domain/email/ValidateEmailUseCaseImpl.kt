package com.aksio.features.authentication.domain.email

import androidx.core.util.PatternsCompat
import com.aksio.core.common.state.TextMessage
import com.aksio.features.authentication.R
import javax.inject.Inject

class ValidateEmailUseCaseImpl @Inject constructor() : ValidateEmailUseCase {

    override suspend fun invoke(email: String): TextMessage? {
        if (email.isBlank() && email.isEmpty()) return TextMessage.ResourceMessage(
            templateRes = R.string.text_validation_error_empty_email
        )
        val isValid = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
        return if (isValid) null else TextMessage.ResourceMessage(
            templateRes = R.string.text_validation_error_invalid_email
        )
    }
}