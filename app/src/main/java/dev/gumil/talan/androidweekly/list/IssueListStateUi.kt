package dev.gumil.talan.androidweekly.list

import android.os.Parcelable
import dev.gumil.kaskade.SingleEvent
import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.androidweekly.mapToModel
import dev.gumil.talan.androidweekly.mapToUiModel
import kotlinx.android.parcel.Parcelize

sealed class IssueListStateUi {

    @Parcelize
    data class Screen(
        val issues: List<IssueEntryUi> = emptyList(),
        val loadingMode: Mode = Mode.IDLE,
        val exception: Throwable? = null
    ) : IssueListStateUi(), Parcelable

    @Parcelize
    data class GoToDetail(
        val issue: IssueEntryUi
    ) : IssueListStateUi(), SingleEvent, Parcelable

    enum class Mode {
        LOADING, IDLE
    }
}

fun IssueListState.mapToUiModel(previousState: IssueListState? = null): IssueListStateUi {
    return when(this) {
        is IssueListState.Screen -> IssueListStateUi.Screen(
            issues.map { it.mapToUiModel() },
            loadingMode.mapToUiModel()
        )
        is IssueListState.Error -> {
            if (previousState is IssueListState.Screen) {
                IssueListStateUi.Screen(
                    previousState.issues.map { it.mapToUiModel() },
                    previousState.loadingMode.mapToUiModel(),
                    exception
                )
            } else error("Mapping not supported")

        }
        is IssueListState.GoToDetail -> IssueListStateUi.GoToDetail(issue.mapToUiModel())
    }
}

fun IssueListState.Mode.mapToUiModel(): IssueListStateUi.Mode {
    return when(this) {
        IssueListState.Mode.LOADING -> IssueListStateUi.Mode.LOADING
        IssueListState.Mode.IDLE -> IssueListStateUi.Mode.IDLE
    }
}

fun IssueListStateUi.mapToModel(): IssueListState {
    return when(this) {
        is IssueListStateUi.Screen -> IssueListState.Screen(
            issues.map { it.mapToModel() },
            loadingMode.mapToModel()
        )
        is IssueListStateUi.GoToDetail -> IssueListState.GoToDetail(issue.mapToModel())
    }
}

fun IssueListStateUi.Mode.mapToModel(): IssueListState.Mode {
    return when(this) {
        IssueListStateUi.Mode.LOADING -> IssueListState.Mode.LOADING
        IssueListStateUi.Mode.IDLE -> IssueListState.Mode.IDLE
    }
}
