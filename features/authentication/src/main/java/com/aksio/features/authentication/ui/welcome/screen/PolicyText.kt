package com.aksio.features.authentication.ui.welcome.screen

import android.text.Annotation
import android.text.SpannedString
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.aksio.core.designsystem.theme.AppTheme
import com.aksio.features.authentication.R

@Composable
fun PrivacyText(
    modifier: Modifier = Modifier,
    termsLink: String,
    policyLink: String
) {
    val uriHandler = LocalUriHandler.current
    val resources = LocalContext.current.resources
    val text = resources.getText(R.string.privacy_text)
    val spannedText = SpannedString(text)
    val annotations = spannedText.getSpans(0, text.length, Annotation::class.java)

    val annotatedBuilder = AnnotatedString.Builder().apply {
        append(text)
    }

    for (annotation in annotations) {
        if (annotation.key == "link") {
            val start = spannedText.getSpanStart(annotation)
            val end = spannedText.getSpanEnd(annotation)
            val link = if (annotation.value == "terms") termsLink else policyLink
            val hyperLinkStyle = SpanStyle(
                color = AppTheme.colors.buttonPrimary
            )
            annotatedBuilder.addStyle(
                style = hyperLinkStyle,
                start = start,
                end = end
            )
            annotatedBuilder.addStringAnnotation(
                tag = "link",
                annotation = link,
                start = start,
                end = end
            )
        }
    }

    val annotatedString = annotatedBuilder.toAnnotatedString()

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        style = AppTheme.typography.bodyXs.copy(
            textAlign = TextAlign.Center
        ),
        onClick = { offset ->
            annotatedString
                .getStringAnnotations(offset, offset)
                .firstOrNull()
                ?.let { span -> uriHandler.openUri(span.item) }
        },
    )

}

@Preview
@Composable
private fun PrivacyTextPreview() {
    AppTheme {
        PrivacyText(
            termsLink = "",
            policyLink = ""
        )
    }
}
