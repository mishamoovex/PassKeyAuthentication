package com.aksio.core.common.core.messenger.error

import com.aksio.core.common.core.messenger.base.BaseMessenger
import com.aksio.core.common.core.messenger.base.Messenger
import com.aksio.core.common.state.TextMessage


/**
 * An extension to the [BaseMessenger] that provide additional functionality of
 * converting [Throwable] into display messages.
 */
interface ErrorMessenger : Messenger {

    /**
     * Converts [Throwable] into [TextMessage] and schedules it for displaying.
     *
     * @param throwable that should be parsed to display message
     */
    fun showError(throwable: Throwable)
}