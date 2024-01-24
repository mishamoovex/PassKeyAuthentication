package com.aksio.authentication.ui.state

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aksio.core.common.state.SnackbarMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
@ReadOnlyComposable
fun rememberResources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Composable
internal fun rememberMainScreenState(
    resources: Resources = rememberResources(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(resources, navController, coroutineScope) {
    MainScreenState(snackbarHostState, navController, resources, coroutineScope)
}

@Stable
internal class MainScreenState(
    val snackbarHostState: SnackbarHostState,
    val navController: NavHostController,
    private val resources: Resources,
    private val coroutineScope: CoroutineScope,
) {

    private val messages: MutableStateFlow<List<SnackbarMessage>> = MutableStateFlow(emptyList())

    init {
        observeDisplayMessages()
    }

    fun showMessage(message: SnackbarMessage) {
        messages.update { currentMessages -> currentMessages + message }
    }

    private fun observeDisplayMessages() {
        coroutineScope.launch {
            messages.collect { currentMessages ->
                if (currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    snackbarHostState.showSnackbar(message.getDisplayText())
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

    private fun SnackbarMessage.getDisplayText() = when (this) {
        is SnackbarMessage.ResourceMessage ->
            resources.getString(templateRes, * placeholders.toTypedArray())

        is SnackbarMessage.StringMessage -> value
    }

}