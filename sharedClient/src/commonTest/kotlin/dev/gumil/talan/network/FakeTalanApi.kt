package dev.gumil.talan.network

import kotlinx.coroutines.delay

internal class FakeTalanApi : TalanApi {
    override suspend fun getAndroidWeeklyIssues(): List<Issue> {
        delay(200)
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
