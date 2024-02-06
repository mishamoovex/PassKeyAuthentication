package com.aksio.authentication.ui.state

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aksio.core.common.state.TextMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
internal fun rememberMainScreenState(
    context: Context = LocalContext.current,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(context, navController, coroutineScope) {
    MainScreenState(snackbarHostState, navController, context, coroutineScope)
}

@Stable
internal class MainScreenState(
    val snackbarHostState: SnackbarHostState,
    val navController: NavHostController,
    private val context: Context,
    private val coroutineScope: CoroutineScope,
) {

    val isTopBarNavigationEnabled = navController.visibleEntries
        .map { it.size > 1 }
        .distinctUntilChanged()
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(500),
            initialValue = false
        )

    private val messages: MutableStateFlow<List<TextMessage>> = MutableStateFlow(emptyList())

    init {
        observeDisplayMessages()
        navController.visibleEntries
    }

    fun showMessage(message: TextMessage) {
        messages.update { currentMessages -> currentMessages + message }
    }

    private fun observeDisplayMessages() {
        coroutineScope.launch {
            messages.collect { currentMessages ->
                if (currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    snackbarHostState.showSnackbar(message.asDisplayText(context))
                    setMessageShown(message.id)
                }
            }
        }
    }

    private fun setMessageShown(messageId: UUID) {
        messages.update { currentMessages ->
            currentMessages.filterNot { it.id == messageId }
        }
    }

}