package com.aksio.features.authentication.ui.email.verify.state

import com.aksio.core.common.state.ActionButtonState
import java.time.OffsetDateTime

data class EmailVerificationUiState(
    val actionButtonState: ActionButtonState = ActionButtonState(),
    val deadline: OffsetDateTime = OffsetDateTime.now()
)