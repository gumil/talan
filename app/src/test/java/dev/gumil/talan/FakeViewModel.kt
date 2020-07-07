package dev.gumil.talan

import dev.gumil.kaskade.Action
import dev.gumil.kaskade.State
import dev.gumil.talan.util.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class FakeViewModel<A: Action, S: State>(
    initialState: S
): ViewModel<A, S> {
    private var isCleared = false

    override val state: StateFlow<S> get() = _state

    private var _state = MutableStateFlow(initialState)

    private var dispatchedAction: A? = null

    override fun dispatch(action: A) {
        dispatchedAction = action
    }

    override fun clear() {
        isCleared = true
    }

    fun givenNextState(state: S) {
        _state.value = state
    }

    fun verifyCleared() {
        assertTrue(isCleared)
    }

    fun verifyDispatchedAction(action: A?) {
        assertEquals(action, dispatchedAction)
        dispatchedAction = null
    }
}
