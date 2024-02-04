package com.aksio.features.authentication.domain.email

import androidx.core.util.PatternsCompat
import com.aksio.core.common.state.TextMessage
import com.aksio.core.hilt.DispatcherComputation
import com.aksio.features.authentication.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ValidateEmailUseCaseImpl @Inject constructor(
    @DispatcherComputation private val computationDispatcher: CoroutineDispatcher
) : ValidateEmailUseCase {

    override suspend fun invoke(email: String): TextMessage? {
        if (email.isBlank() && email.isEmpty()) return TextMessage.ResourceMessage(
            templateRes = R.string.text_validation_error_empty_email
        )
        return withContext(computationDispatcher) {
            val isValid = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
            if (isValid) null else TextMessage.ResourceMessage(
                templateRes = R.string.text_validation_error_invalid_email
            )
        }
    }
}