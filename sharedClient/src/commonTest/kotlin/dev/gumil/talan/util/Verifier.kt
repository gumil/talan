package dev.gumil.talan.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class Verifier<T> {

    val function: (T) -> Unit = {
        invokedValues.add(it)
    }

    private var invokedValues = mutableListOf<T>()

    fun verifyInvokedWithValue(value: T, times: Int = 1) {
        assertEquals(times, invokedValues.filter { it == value }.size)
    }

    fun verifyNoInvocations() {
        assertTrue { invokedValues.isEmpty() }
    }

    fun verifyOrder(verifyBuilder: OrderedBuilder<T>.() -> Unit) {
        val orderedBuilder = OrderedBuilder<T>()
        verifyBuilder(orderedBuilder)
        orderedBuilder.values.forEachIndexed { index, value ->
            assertEquals(value, invokedValues[index])
        }
    }

    internal class OrderedBuilder<T> {
        private val _values = mutableListOf<T>()

        val values: List<T> = _values

        fun verify(value: T) {
            _values.add(value)
        }
    }
}

fun <T> Flow<T>.verifyCollect(coroutineScope: CoroutineScope, action: (value: T) -> Unit) =
    onEach { action(it) }.launchIn(coroutineScope)
