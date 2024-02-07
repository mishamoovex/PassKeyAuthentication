package com.aksio.core.common.vm

import androidx.lifecycle.ViewModel
import com.aksio.core.common.core.messenger.error.ErrorMessenger
import com.aksio.core.tests_shared.MainDispatcherRule
import com.aksio.core.tests_shared.fake.common.FakeErrorMessenger
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelRequestHandlerTest {

    private class FakeViewModel(
        private val messenger: ErrorMessenger
    ) : ViewModel(),
        ErrorMessenger by messenger

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var messenger: FakeErrorMessenger
    private lateinit var viewModel: FakeViewModel

    @Before
    fun setUp() {
        messenger = FakeErrorMessenger()
        viewModel = FakeViewModel(messenger)
    }

    @Test
    fun `SHOULD trigger execute() WHEN execution starts`() = runTest {
        //Given a request counter to verify that execution starts
        val requestCounter = AtomicInteger(0)
        //When a request execution starts
        viewModel.executeAction { requestCounter.set(requestCounter.get() + 1) }
        //Than the request count should be increased
        requestCounter.get().shouldBe(expected = 1)
    }

    @Test
    fun `SHOULD set isLoading(true) WHEN execution starts`() = runTest {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
        //Given an inactive loader state
        val isLoading = AtomicBoolean(false)
        //When a request execution starts
        viewModel.executeAction(
            onLoading = { isLoading.set(it) },
            execute = {}
        )
        //Then the loader state should be set True
        isLoading.get().shouldBeTrue()
    }

    @Test
    fun `SHOULD set isLoading(false) WHEN execution completes`() = runTest {
        //Given an active loader state
        val isLoading = AtomicBoolean(true)
        //When a request execution completes
        viewModel.executeAction(
            onLoading = { isLoading.set(it) },
            execute = {}
        )
        //Then the loader state should be set True
        isLoading.get().shouldBeFalse()
    }

    @Test
    fun `SHOULD set isLoading(false) WHEN execution completes with an exception`() = runTest {
        //Given an active loader state
        val isLoading = AtomicBoolean(true)
        //When a request execution completes with an exception
        viewModel.executeAction(
            onLoading = { isLoading.set(it) },
            execute = { throw Exception() }
        )
        //Then the loader state should be set True
        isLoading.get().shouldBeFalse()
    }

    @Test
    fun `SHOULD set a display error WHEN execution completes with an exception`() = runTest {
        //Given a View Model in the default state

        //When a request execution completes with an exception
        //and onError() handler isn't provided
        viewModel.executeAction { throw Exception() }
        //Than a display error should be set
        viewModel.displayMessages.value.shouldContain(messenger.errorMessage)
    }

    @Test
    fun `SHOULD trigger onError() WHEN execution completes with an exception and onError callback is provided`() =
        runTest {
            //Given a request counter to verify that onError() handler invoked
            val requestCounter = AtomicInteger(0)
            //When a request execution completes with an exception
            viewModel.executeAction(
                onError = { requestCounter.set(requestCounter.get() + 1) },
                execute = { throw Exception() }
            )
            //Than the request count should be increased
            requestCounter.get().shouldBe(expected = 1)
        }

}