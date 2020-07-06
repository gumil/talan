package dev.gumil.talan.androidweekly

import android.os.Parcelable
import dev.gumil.talan.network.EntryType
import dev.gumil.talan.network.IssueEntry
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IssueEntryUi(
    val title: String,
    val description: String,
    val image: String,
    val link: String,
    val host: String,
    val isSponsored: Boolean,
    val type: EntryTypeUi
): Parcelable

enum class EntryTypeUi{
    ARTICLE,
    JOB,
    VIDEO,
    LIBRARY,
    PATREON,
    UNKNOWN,
    SPONSORED
}

fun IssueEntry.mapToUiModel(): IssueEntryUi {
    return IssueEntryUi(
        title,
        description,
        image,
        link,
        host,
        isSponsored,
        type.mapToUiModel()
    )
}

fun EntryType.mapToUiModel(): EntryTypeUi {
    return when(this) {
        EntryType.ARTICLE -> EntryTypeUi.ARTICLE
        EntryType.JOB -> EntryTypeUi.JOB
        EntryType.VIDEO -> EntryTypeUi.VIDEO
        EntryType.LIBRARY -> EntryTypeUi.LIBRARY
        EntryType.PATREON -> EntryTypeUi.PATREON
        EntryType.UNKNOWN -> EntryTypeUi.UNKNOWN
        EntryType.SPONSORED -> EntryTypeUi.SPONSORED
    }
}

fun IssueEntryUi.mapToModel(): IssueEntry {
    return IssueEntry(
        title,
        description,
        image,
        link,
        host,
        isSponsored,
        type.mapToModel()
    )
}

fun EntryTypeUi.mapToModel(): EntryType {
    return when(this) {
        EntryTypeUi.ARTICLE -> EntryType.ARTICLE
        EntryTypeUi.JOB -> EntryType.JOB
        EntryTypeUi.VIDEO -> EntryType.VIDEO
        EntryTypeUi.LIBRARY -> EntryType.LIBRARY
        EntryTypeUi.PATREON -> EntryType.PATREON
        EntryTypeUi.UNKNOWN -> EntryType.UNKNOWN
        EntryTypeUi.SPONSORED -> EntryType.SPONSORED
    }
}
