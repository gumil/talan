package dev.gumil.talan

import kotlinx.coroutines.CoroutineScope

expect fun runTest(block: suspend CoroutineScope.() -> Unit)
