package com.aksio.authentication.ui.screen

import com.aksio.authentication.ui.navigation.MainGraph
import com.aksio.authentication.ui.state.MainScreenUiState
import com.aksio.core.common.vm.BaseVm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenVm @Inject constructor() : BaseVm() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        executionScope.launch {
            delay(500)
            _uiState.update { it.copy(route = MainGraph.Authentication.route) }
        }
    }
}