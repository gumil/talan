package dev.gumil.talan.androidweekly.list

import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize
import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.util.StateUi

sealed class IssueListState : StateUi {

    @Parcelize
    data class Screen(
        val issues: List<IssueEntryUi> = emptyList(),
        val loadingMode: Mode = Mode.IDLE,
        val exception: Throwable? = null
    ) : IssueListState(), Parcelable

    @Parcelize
    data class GoToDetail(
        val issue: IssueEntryUi
    ) : IssueListState(), Parcelable

    enum class Mode {
        LOADING, IDLE
    }
}
