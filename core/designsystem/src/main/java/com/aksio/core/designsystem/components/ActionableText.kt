package com.aksio.core.designsystem.components

import android.text.Annotation
import android.text.SpannedString
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.aksio.core.designsystem.theme.AppTheme

@Composable
fun ActionableText(
    modifier: Modifier = Modifier,
    text: String,
    action: () -> Unit
) {
    val spannedText = SpannedString(text)
    val annotations = spannedText.getSpans(0, text.length, Annotation::class.java)

    val annotatedBuilder = AnnotatedString.Builder().apply {
        append(text)
    }

    for (annotation in annotations) {
        if (annotation.key == "action") {
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
