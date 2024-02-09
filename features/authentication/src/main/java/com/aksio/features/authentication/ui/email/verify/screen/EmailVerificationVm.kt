package com.aksio.features.authentication.ui.email.verify.screen

import androidx.lifecycle.ViewModel
import com.aksio.core.common.core.messenger.error.ErrorMessenger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmailVerificationVm @Inject constructor(
    private val messenger: ErrorMessenger
) : ViewModel(),
    ErrorMessenger by messenger {

}