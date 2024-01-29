package com.aksio.core.designsystem.components

import android.text.Annotation
import android.text.SpannedString
import androidx.annotation.StringRes
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.aksio.core.designsystem.R
import com.aksio.core.designsystem.theme.AppTheme

@Composable
fun ActionableText(
    modifier: Modifier = Modifier,
    @StringRes actionableText: Int,
    action: () -> Unit
) {
    val resources = LocalContext.current.resources
    val text = resources.getText(actionableText)
    val spannedText = SpannedString(text)
    val annotations = spannedText.getSpans(0, text.length, Annotation::class.java)

    val annotatedBuilder = AnnotatedString.Builder().apply {
        append(text)
    }

    for (annotation in annotations) {
        if (annotation.value == "click") {
            val start = spannedText.getSpanStart(annotation)
            val end = spannedText.getSpanEnd(annotation)
            val hyperLinkStyle = SpanStyle(
                color = AppTheme.colors.buttonPrimary,
                fontWeight = FontWeight.Bold
            )
            annotatedBuilder.addStyle(
                style = hyperLinkStyle,
                start = start,
                end = end
            )
            annotatedBuilder.addStringAnnotation(
                tag = "action",
                annotation = "click",
                start = start,
                end = end
            )
        }
    }

    val annotatedString = annotatedBuilder.toAnnotatedString()

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        style = AppTheme.typography.bodyM.copy(
            textAlign = TextAlign.Center,
            color = AppTheme.colors.textHilt
        ),
        onClick = { offset ->
            annotatedString
                .getStringAnnotations(offset, offset)
                .firstOrNull()
                ?.let { action.invoke() }
        },
    )
}

@Preview
@Composable
private fun ActionableTextPreview() {
    AppTheme {
        ActionableText(
            actionableText = R.string.actionable_text_preview,
            action = {}
        )
    }
}
