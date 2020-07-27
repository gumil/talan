package dev.gumil.talan

import dev.gumil.talan.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

class TestDispatcherProvider: DispatcherProvider {
    private val testDispatcher by lazy { TestCoroutineDispatcher() }

    override fun io(): CoroutineDispatcher {
        return testDispatcher
    }

    override fun main(): CoroutineDispatcher {
        return testDispatcher
    }

    fun advanceUntilIdle() {
        testDispatcher.advanceUntilIdle()
    }
}
