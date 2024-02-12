package com.aksio.features.authentication.ui.email.verify.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aksio.core.common.core.messenger.error.ErrorMessenger
import com.aksio.core.common.state.ActionButtonState
import com.aksio.core.common.vm.executeAction
import com.aksio.core.hilt.DispatcherComputation
import com.aksio.core.repository.authentication.repository.AuthenticationRepository
import com.aksio.features.authentication.ui.email.verify.state.EmailVerificationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import java.time.Clock
import java.time.OffsetDateTime
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class EmailVerificationVm @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val messenger: ErrorMessenger,
    private val clock: Clock,
    @DispatcherComputation private val computationDispatcher: CoroutineDispatcher
) : ViewModel(),
    ErrorMessenger by messenger {

    private companion object {
        val emailVerificationDelay = Duration.parse("5m")
    }

    private val actionDeadline = authenticationRepository.observeCurrentUser().map { user ->
        OffsetDateTime.now(clock).applyVerificationDelay(user?.emailVerificationSentAt)
    }

    private val actionButtonState = MutableStateFlow(
        ActionButtonState(
            isEnabled = true,
            onClicked = ::sendVerificationEmail
        )
    )

    val uiState: StateFlow<EmailVerificationUiState> = combine(
        actionDeadline,
        actionButtonState
    ) { deadline, button ->
        EmailVerificationUiState(
            actionButtonState = button,
            deadline = deadline
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = EmailVerificationUiState()
    )

    private fun sendVerificationEmail() {
        executeAction(
            onLoading = { isLoading -> actionButtonState.update { it.copy(isLoading) } },
            execute = { authenticationRepository.sendVerificationEmail() }
        )
    }

    private suspend fun OffsetDateTime.applyVerificationDelay(
        verifiedAt: OffsetDateTime?
    ): OffsetDateTime {

        if (verifiedAt == null) return this

        return withContext(computationDispatcher) {
            val currentSeconds = this@applyVerificationDelay.toEpochSecond().seconds
            val verifiedAtSeconds = verifiedAt.toEpochSecond().seconds
            val passed = currentSeconds - verifiedAtSeconds

            if (passed < emailVerificationDelay) {
                (emailVerificationDelay - passed).toComponents { minutes, seconds, _ ->
                    this@applyVerificationDelay
                        .plusMinutes(minutes)
                        .plusSeconds(seconds.toLong())
                }
            } else {
                this@applyVerificationDelay
            }
        }
    }

}