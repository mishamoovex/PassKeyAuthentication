package com.aksio.core.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aksio.core.common.designsystem.R
import com.aksio.core.designsystem.theme.AppTheme

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    isBackNavigationEnabled: Boolean
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (isBackNavigationEnabled) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onBack),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back_navigation),
                    contentDescription = "Back button",
                    modifier = Modifier.size(32.dp),
                    tint = AppTheme.colors.navigationButton
                )
            }
        }
    }
}

@Composable
@Preview
private fun ToolbarPreview_NotVisible() {
    AppTheme {
        Toolbar(
            onBack = {},
            isBackNavigationEnabled = false
        )
    }
}

@Composable
@Preview
private fun ToolbarPreview_Visible() {
    AppTheme {
        Toolbar(
            onBack = {},
            isBackNavigationEnabled = true
        )
    }
}