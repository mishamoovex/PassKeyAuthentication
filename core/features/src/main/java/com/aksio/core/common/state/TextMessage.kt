package com.aksio.core.common.state

import android.content.Context
import androidx.annotation.StringRes
import com.aksio.core.common.R
import java.util.UUID

sealed class TextMessage(
    val id: UUID = UUID.randomUUID()
) {
    data class ResourceMessage(
        @StringRes val templateRes: Int,
        val placeholders: List<String> = emptyList()
    ) : TextMessage()

    data class StringMessage(
        val value: String
    ) : TextMessage()

    data class BuildString(
        @StringRes val base: Int,
        val cases: List<Int> = emptyList(),
    ) : TextMessage()

    fun asDisplayText(context: Context) = when (this) {
        is ResourceMessage ->
            context.resources.getString(templateRes, * placeholders.toTypedArray())

        is StringMessage -> value

        is BuildString -> buildString {
            append(context.resources.getString(base))

            cases.forEachIndexed { index, case ->
                if (index > 0) {
                    val appendable = if (cases.size > 1 && index == cases.size - 1) {
                        append(" ")
                        context.resources.getString(R.string.text_field_separation_and)
                    } else {
                        ','
                    }
                    append(appendable)
                }
                append(' ')
                append(context.resources.getString(case))
            }
        }
    }
}
