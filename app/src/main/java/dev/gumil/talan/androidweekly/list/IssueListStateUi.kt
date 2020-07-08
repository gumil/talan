package dev.gumil.talan.androidweekly.list

import android.os.Parcel
import android.os.Parcelable
import dev.gumil.kaskade.SingleEvent
import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.androidweekly.mapToModel
import dev.gumil.talan.androidweekly.mapToUiModel

/**
 * Jetpack Compose clashes with kotlin android extensions.
 * Parcelable implementations are a workaround for now.
 */
sealed class IssueListStateUi {

    data class Screen(
        val issues: List<IssueEntryUi> = emptyList(),
        val loadingMode: Mode = Mode.IDLE,
        val exception: Throwable? = null
    ) : IssueListStateUi(), Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.createTypedArrayList(IssueEntryUi.CREATOR)!!,
            Mode.values()[parcel.readInt()]
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeTypedList(issues)
            parcel.writeInt(loadingMode.ordinal)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Screen> {
            override fun createFromParcel(parcel: Parcel): Screen {
                return Screen(parcel)
            }

            override fun newArray(size: Int): Array<Screen?> {
                return arrayOfNulls(size)
            }
        }

    }

    data class GoToDetail(
        val issue: IssueEntryUi
    ) : IssueListStateUi(), SingleEvent, Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readParcelable<IssueEntryUi>(IssueEntryUi::class.java.classLoader)!!
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeParcelable(issue, flags)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<GoToDetail> {
            override fun createFromParcel(parcel: Parcel): GoToDetail {
                return GoToDetail(parcel)
            }

            override fun newArray(size: Int): Array<GoToDetail?> {
                return arrayOfNulls(size)
            }
        }

    }

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
