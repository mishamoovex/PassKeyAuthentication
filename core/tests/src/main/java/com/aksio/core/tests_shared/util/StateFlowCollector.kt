package com.aksio.core.tests_shared.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher

/**
 *  Activates [StateFlow] by assigning a collector.
 *
 *  We are not able to test [StateFlow]'s that have been created by the stateIn() function
 *  using flow.value before it gets at least one collector.
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun <T> TestScope.setFlowCollector(flow: StateFlow<T>) {
    backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
        flow.collect()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> TestScope.collectFromFlow(flow: StateFlow<T>): List<T> {
    val results = mutableListOf<T>()
    backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
        flow.toList(results)
    }
    return results
}