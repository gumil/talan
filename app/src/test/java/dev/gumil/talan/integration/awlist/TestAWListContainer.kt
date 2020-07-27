package dev.gumil.talan.integration.awlist

import dev.gumil.talan.androidweekly.list.AWListContainer
import dev.gumil.talan.androidweekly.list.IssueListAction
import dev.gumil.talan.androidweekly.list.IssueListStateUi

class TestAWListContainer: AWListContainer {
    override var actions: (IssueListAction) -> Unit = {}
    override val currentState: IssueListStateUi
        get() = _states.last()
    val states: List<IssueListStateUi> get() = _states.toList()
    private val _states = mutableListOf<IssueListStateUi>()

    override fun setState(issueListStateUi: IssueListStateUi) {
        _states.add(issueListStateUi)
    }

    override fun render() {}
}
