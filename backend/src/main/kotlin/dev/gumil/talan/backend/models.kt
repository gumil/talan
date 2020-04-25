package dev.gumil.talan.backend

data class Issue(
    val title: String,
    val link: String,
    val entries: List<IssueEntry>,
    val publishDate: String
)

data class IssueEntry(
    val title: String,
    val description: String,
    val image: String,
    val link: String,
    val host: String,
    val isSponsored: Boolean,
    val type: EntryType
)

enum class EntryType{
    ARTICLE,
    JOB,
    VIDEO,
    LIBRARY,
    PATREON,
    UNKNOWN,
    SPONSORED;

    companion object {
        fun from(text: String): EntryType {
            return when(text) {
                "Patreon" -> PATREON
                "Articles & Tutorials" -> ARTICLE
                "Libraries & Code" -> LIBRARY
                "Jobs" -> JOB
                "Videos & Podcasts" -> VIDEO
                "Sponsored" -> SPONSORED
                else -> UNKNOWN
            }
        }
    }
}
