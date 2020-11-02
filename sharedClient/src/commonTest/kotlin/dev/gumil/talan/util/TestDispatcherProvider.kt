package dev.gumil.talan.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class TestDispatcherProvider: DispatcherProvider {
    override fun io(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }

    override fun main(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }
}
