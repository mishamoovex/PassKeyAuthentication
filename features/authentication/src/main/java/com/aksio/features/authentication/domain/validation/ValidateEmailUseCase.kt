package com.aksio.features.authentication.domain.validation

import androidx.core.util.PatternsCompat
import com.aksio.core.common.state.TextMessage
import com.aksio.core.hilt.DispatcherComputation
import com.aksio.features.authentication.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
    @DispatcherComputation private val computationDispatcher: CoroutineDispatcher
) : StringValidationUseCase {

    override suspend fun invoke(value: String): TextMessage? {
        if (value.isBlank() && value.isEmpty()) return TextMessage.ResourceMessage(
            templateRes = R.string.text_validation_error_empty_email
        )
        return withContext(computationDispatcher) {
            val isValid = PatternsCompat.EMAIL_ADDRESS.matcher(value).matches()
            if (isValid) null else TextMessage.ResourceMessage(
                templateRes = R.string.text_validation_error_invalid_email
            )
        }
    }
}