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
        var requestCounter = 0
        //When a request execution starts
        viewModel.executeAction { requestCounter++ }
        //Than the request count should be increased
        requestCounter.shouldBe(expected = 1)
    }

    @Test
    fun `SHOULD set isLoading(true) WHEN execution starts`() = runTest {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
        //Given an inactive loader state
        var isLoading = false
        //When a request execution starts
        viewModel.executeAction(
            onLoading = { isLoading = it },
            execute = {}
        )
        //Then the loader state should be set True
        isLoading.shouldBeTrue()
    }

    @Test
    fun `SHOULD set isLoading(false) WHEN execution completes`() = runTest {
        //Given an active loader state
        var isLoading = true
        //When a request execution completes
        viewModel.executeAction(
            onLoading = { isLoading = it },
            execute = {}
        )
        //Then the loader state should be set True
        isLoading.shouldBeFalse()
    }

    @Test
    fun `SHOULD set isLoading(false) WHEN execution completes with an exception`() = runTest {
        //Given an active loader state
        var isLoading = true
        //When a request execution completes with an exception
        viewModel.executeAction(
            onLoading = { isLoading = it },
            execute = { throw Exception() }
        )
        //Then the loader state should be set True
        isLoading.shouldBeFalse()
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
            var requestCounter = 0
            //When a request execution completes with an exception
            viewModel.executeAction(
                onError = { requestCounter++ },
                execute = { throw Exception() }
            )
            //Than the request count should be increased
            requestCounter.shouldBe(expected = 1)
        }

}