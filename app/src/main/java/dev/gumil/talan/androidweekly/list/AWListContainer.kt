package dev.gumil.talan.androidweekly.list

import dev.gumil.talan.acorn.ComposeContainer

internal interface AWListContainer: ComposeContainer {
    var actions: (IssueListAction) -> Unit
    val currentState: IssueListStateUi

    fun setState(issueListStateUi: IssueListStateUi)
}
