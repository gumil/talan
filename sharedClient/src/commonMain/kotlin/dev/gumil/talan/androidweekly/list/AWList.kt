package dev.gumil.talan.androidweekly.list

import com.arkivanov.decompose.value.Value
import dev.gumil.talan.androidweekly.IssueEntryUi

interface AWList {

    val model: Model

    interface Model : Events {
        val state: Value<IssueListState>
    }

    interface Events {
        fun onItemClicked(issueEntry: IssueEntryUi)

        fun refresh()
    }
}
