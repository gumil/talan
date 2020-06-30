package dev.gumil.talan.androidweekly.list

import dev.gumil.kaskade.Action
import dev.gumil.kaskade.SingleEvent
import dev.gumil.kaskade.State
import dev.gumil.talan.network.IssueEntry

internal sealed class IssueListState : State {

    data class Screen(
        val issues: List<IssueEntry> = emptyList(),
        val loadingMode: Mode = Mode.IDLE
    ) : IssueListState()

    data class Error(
        val exception: Throwable
    ) : IssueListState(), SingleEvent

    data class GoToDetail(
        val issue: IssueEntry
    ) : IssueListState(), SingleEvent

    enum class Mode {
        LOADING, IDLE
    }
}

internal sealed class IssueListAction : Action {

    object Refresh : IssueListAction()

    data class OnItemClick(
        val issue: IssueEntry
    ) : IssueListAction()
}
