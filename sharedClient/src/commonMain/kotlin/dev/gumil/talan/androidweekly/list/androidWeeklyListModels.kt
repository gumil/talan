package dev.gumil.talan.androidweekly.list

import dev.gumil.kaskade.Action
import dev.gumil.kaskade.SingleEvent
import dev.gumil.kaskade.State
import dev.gumil.talan.network.IssueEntry

sealed class IssueListState : State {

    data class Screen(
        val issues: List<IssueEntry> = emptyList(),
        val loadingMode: Mode = Mode.IDLE,
        val exception: Throwable? = null
    ) : IssueListState()

    data class GoToDetail(
        val issue: IssueEntry
    ) : IssueListState(), SingleEvent

    enum class Mode {
        LOADING, IDLE
    }
}

sealed class IssueListAction : Action {

    object Refresh : IssueListAction()

    data class OnItemClick(
        val issue: IssueEntry
    ) : IssueListAction()
}
