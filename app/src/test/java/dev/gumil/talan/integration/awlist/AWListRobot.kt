package dev.gumil.talan.integration.awlist

import com.nhaarman.acorn.testing.TestContext
import dev.gumil.talan.androidweekly.list.IssueListStateUi
import org.junit.Assert.assertEquals

fun TestContext.awList(f: AWListRobot.() -> Unit) {
    AWListRobot(this).f()
}

class AWListRobot(
    private val context: TestContext
) {
    private val container = context.container<TestAWListContainer>()

    private val state get() = container.currentState

    fun verifyState(state: IssueListStateUi) {
        assertEquals(state, container.currentState)
    }

    fun pressBack() {
        context.pressBack()
    }
}
