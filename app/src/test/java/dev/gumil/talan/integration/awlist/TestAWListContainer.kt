package dev.gumil.talan.integration.awlist

import androidx.compose.FrameManager
import androidx.compose.MutableState
import androidx.compose.mutableStateOf
import dev.gumil.talan.androidweekly.list.AWListContainer
import dev.gumil.talan.androidweekly.list.IssueListAction
import dev.gumil.talan.androidweekly.list.IssueListStateUi

class TestAWListContainer: AWListContainer {
    private val state: MutableState<IssueListStateUi> = mutableStateOf(IssueListStateUi.Screen())
    override var actions: (IssueListAction) -> Unit = {}
    override val currentState: IssueListStateUi
        get() = FrameManager.framed {
            state.value
        }

    override fun setState(issueListStateUi: IssueListStateUi) {
        FrameManager.framed {
            state.value = issueListStateUi
        }
    }

    override fun render() {}
}
