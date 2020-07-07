package dev.gumil.talan.util

import dev.gumil.kaskade.Action
import dev.gumil.kaskade.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
interface ViewModel<A: Action, S: State> {

    val state: StateFlow<S>

    fun dispatch(action: A)

    fun clear()
}
