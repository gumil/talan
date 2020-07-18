package dev.gumil.talan.network

internal class FakeTalanApi : TalanApi {
    override suspend fun getAndroidWeeklyIssues(): List<Issue> {
        return listOf(
            Issue(
                "issue 1",
                "link",
                listOf(
                    IssueEntry(
                        "title",
                        "description",
                        "image",
                        "link",
                        "hosted",
                        false,
                        EntryType.ARTICLE
                    )
                ),
                "01/01/2020"
            )
        )
    }
}
