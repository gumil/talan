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

    fun verifyStates(vararg states: IssueListStateUi) {
        states.forEachIndexed { index, issueListStateUi ->
            assertEquals(issueListStateUi, container.states[index])
        }
    }

    fun pressBack() {
        context.pressBack()
    }
}
