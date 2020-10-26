package dev.gumil.talan.network

import dev.gumil.talan.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import kotlin.coroutines.CoroutineContext

class NativeDispatcherProvider: DispatcherProvider {
    override fun io(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    override fun main(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}
