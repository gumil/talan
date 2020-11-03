package dev.gumil.talan.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class DefaultDispatcherProvider : DispatcherProvider {
    override fun io(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    override fun main(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}
