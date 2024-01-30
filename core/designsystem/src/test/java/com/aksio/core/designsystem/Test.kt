package com.aksio.core.designsystem

import io.kotest.matchers.booleans.shouldBeFalse
import org.junit.Test

class Test {

    @Test
    fun testToFail() {
        val value = true
        value.shouldBeFalse()
    }
}