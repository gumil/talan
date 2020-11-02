package dev.gumil.talan.androidweekly

import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize
import dev.gumil.talan.network.EntryType
import dev.gumil.talan.network.IssueEntry

@Parcelize
data class IssueEntryUi(
    val title: String,
    val description: String,
    val image: String,
    val link: String,
    val host: String,
    val isSponsored: Boolean,
    val type: EntryType
): Parcelable

fun IssueEntry.toUiModel(): IssueEntryUi {
    return IssueEntryUi(
        title,
        description,
        image,
        link,
        host,
        isSponsored,
        type
    )
}
