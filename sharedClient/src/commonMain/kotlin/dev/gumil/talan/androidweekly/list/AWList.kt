package dev.gumil.talan.androidweekly.list

import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize
import com.arkivanov.decompose.value.Value
import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.util.StateUi

interface AWList {

    val model: Model

    interface Model : Events {
        val state: Value<Screen>
    }

    @Parcelize
    data class Screen(
        val issues: List<IssueEntryUi> = emptyList(),
        val loadingMode: Mode = Mode.IDLE,
        val exception: Throwable? = null
    ) : StateUi, Parcelable

    enum class Mode {
        LOADING, IDLE
    }

    interface Events {
        fun onItemClicked(issueEntry: IssueEntryUi)

        fun refresh()
    }
}
