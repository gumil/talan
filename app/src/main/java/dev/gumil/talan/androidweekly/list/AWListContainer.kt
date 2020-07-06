package dev.gumil.talan.androidweekly.list

import androidx.compose.MutableState
import dev.gumil.talan.acorn.ComposeContainer

internal interface AWListContainer: ComposeContainer {
    val state: MutableState<IssueListStateUi>
    var actions: (IssueListAction) -> Unit
}
