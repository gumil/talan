package dev.gumil.talan.network

import dev.gumil.talan.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import kotlin.coroutines.CoroutineContext

class NativeDispatcherProvider: DispatcherProvider {
    override fun io(): CoroutineDispatcher {
        return MainLoopDispatcher
    }

    override fun main(): CoroutineDispatcher {
        return MainLoopDispatcher
    }
}

object MainLoopDispatcher: CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        NSRunLoop.mainRunLoop().performBlock {
            block.run()
        }
    }
}
