package com.aksio.core.designsystem

import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.Test

class FailingTest {

    @Test
    fun failingTest() {
        val value = false
        value.shouldBeTrue()
    }
}