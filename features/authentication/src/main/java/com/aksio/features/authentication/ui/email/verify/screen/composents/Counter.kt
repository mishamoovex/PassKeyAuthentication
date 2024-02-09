package com.aksio.features.authentication.ui.email.verify.screen.composents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aksio.core.common.state.ActionButtonState
import com.aksio.core.designsystem.components.SpinningProgressIndicator
import com.aksio.core.designsystem.theme.AppTheme
import com.aksio.features.authentication.R
import kotlinx.coroutines.delay
import java.time.OffsetDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


@Composable
internal fun Counter(
    modifier: Modifier = Modifier,
    deadline: OffsetDateTime,
    actionButtonState: ActionButtonState
) {
    var remaining by remember(deadline) {
        val nowDuration = OffsetDateTime.now().toEpochSecond().seconds
        val deadlineDuration = deadline.toEpochSecond().seconds
        mutableStateOf(deadlineDuration - nowDuration)
    }

    val showActionBtn by remember {
        derivedStateOf { remaining.isNegative() || remaining == Duration.ZERO }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(percent = 50))
            .clickable(
                enabled = showActionBtn,
                onClick = actionButtonState.onClicked
            ),
        contentAlignment = Alignment.Center
    ) {

        if (showActionBtn) {
            if (actionButtonState.isLoading) {
                SpinningProgressIndicator(
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    text = stringResource(R.string.email_verification_resend_email_btn),
                    style = AppTheme.typography.button.copy(
                        color = AppTheme.colors.onSecondaryButton
                    )
                )
            }
        } else {
            LaunchedEffect(deadline) {
                while (true) {
                    delay(1000)
                    remaining = remaining.minus(1.seconds)
                }
            }
            Text(
                text = stringResource(
                    id = R.string.email_verification_resend_timeout_text,
                    remaining.displayTime()
                ),
                style = AppTheme.typography.bodyM,
            )

        }
    }
}

private fun Duration.displayTime(): String = toComponents { _, minutes, seconds, _ ->
    "${minutes.toTimePart()}:${seconds.toTimePart()}"
}

private fun Int.toTimePart(): String =
    this.toString().padStart(length = 2, padChar = '0')

@Preview
@Composable
private fun CounterPreview_Active() {
    AppTheme {
        Counter(
            deadline = OffsetDateTime.now().plusMinutes(4),
            actionButtonState = ActionButtonState()
        )
    }
}

@Preview
@Composable
private fun CounterPreview_NotActive() {
    AppTheme {
        Counter(
            deadline = OffsetDateTime.now().minusMinutes(4),
            actionButtonState = ActionButtonState()
        )
    }
}

@Preview
@Composable
private fun CounterPreview_Active_Loading() {
    AppTheme {
        Counter(
            deadline = OffsetDateTime.now().minusMinutes(4),
            actionButtonState = ActionButtonState(isLoading = true)
        )
    }
}