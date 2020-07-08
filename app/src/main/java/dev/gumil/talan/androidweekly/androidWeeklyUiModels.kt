package dev.gumil.talan.androidweekly

import android.os.Parcel
import android.os.Parcelable
import dev.gumil.talan.network.EntryType
import dev.gumil.talan.network.IssueEntry

data class IssueEntryUi(
    val title: String,
    val description: String,
    val image: String,
    val link: String,
    val host: String,
    val isSponsored: Boolean,
    val type: EntryTypeUi
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        EntryTypeUi.values()[parcel.readInt()]
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeString(link)
        parcel.writeString(host)
        parcel.writeByte(if (isSponsored) 1 else 0)
        parcel.writeInt(type.ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IssueEntryUi> {
        override fun createFromParcel(parcel: Parcel): IssueEntryUi {
            return IssueEntryUi(parcel)
        }

        override fun newArray(size: Int): Array<IssueEntryUi?> {
            return arrayOfNulls(size)
        }
    }

}

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
