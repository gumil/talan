package dev.gumil.talan.androidweekly.list

import android.os.Parcelable
import dev.gumil.kaskade.SingleEvent
import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.androidweekly.mapToUiModel
import kotlinx.android.parcel.Parcelize

sealed class IssueListStateUi {

    @Parcelize
    data class Screen(
        val issues: List<IssueEntryUi> = emptyList(),
        val loadingMode: Mode = Mode.IDLE
    ) : IssueListStateUi(), Parcelable

    @Parcelize
    data class Error(
        val exception: Throwable
    ) : IssueListStateUi(), SingleEvent, Parcelable

    @Parcelize
    data class GoToDetail(
        val issue: IssueEntryUi
    ) : IssueListStateUi(), SingleEvent, Parcelable

    enum class Mode {
        LOADING, IDLE
    }
}

fun IssueListState.mapToUiModel(): IssueListStateUi {
    return when(this) {
        is IssueListState.Screen -> IssueListStateUi.Screen(
            issues.map { it.mapToUiModel() },
            loadingMode.mapToUiModel()
        )
        is IssueListState.Error -> IssueListStateUi.Error(exception)
        is IssueListState.GoToDetail -> IssueListStateUi.GoToDetail(issue.mapToUiModel())
    }
}

fun IssueListState.Mode.mapToUiModel(): IssueListStateUi.Mode {
    return when(this) {
        IssueListState.Mode.LOADING -> IssueListStateUi.Mode.LOADING
        IssueListState.Mode.IDLE -> IssueListStateUi.Mode.IDLE
    }
}