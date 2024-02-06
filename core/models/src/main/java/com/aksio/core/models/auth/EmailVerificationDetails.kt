package com.aksio.core.models.auth

import java.time.OffsetDateTime

data class EmailVerificationDetails(
    val state: EmailVerificationState,
    val sentAt: OffsetDateTime
)
