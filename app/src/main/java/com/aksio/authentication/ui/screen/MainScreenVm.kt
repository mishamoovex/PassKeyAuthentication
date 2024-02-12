package com.aksio.authentication.ui.screen

import androidx.lifecycle.ViewModel
import com.aksio.authentication.ui.navigation.MainGraph
import com.aksio.authentication.ui.state.MainScreenUiState
import com.aksio.core.common.core.messenger.error.ErrorMessenger
import com.aksio.core.common.vm.executeAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainScreenVm @Inject constructor(
    private val messenger: ErrorMessenger
) : ViewModel(),
    ErrorMessenger by messenger {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        executeAction {
            delay(500)
            _uiState.update {
                it.copy(
                    route = MainGraph.Authentication.route,
                    emailVerificationRequired = true
                )
            }
        }
    }
}