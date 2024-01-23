package com.aksio.authentication.ui.screen

import com.aksio.authentication.ui.state.MainScreenUiState
import com.aksio.core.common.vm.BaseVm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenVm @Inject constructor(): BaseVm() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState = _uiState.asStateFlow()
}