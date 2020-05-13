package dev.gumil.talan.backend

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import dev.gumil.talan.backend.andweekly.AndroidWeeklyApi
import org.junit.Test
import kotlin.test.assertEquals

internal class RootKtTest {

    @Test
    fun `androidWeeklyJson returns success json`() {
        val expected = readFromFile("expected.json")

        val api = mock<AndroidWeeklyApi>()
        val issues = listOf(
            Issue(
                "Android Weekly 1",
                "link 1",
                listOf(
                    IssueEntry(
                        title = "title 1-1",
                        description = "desc 1-1",
                        image = "image 1-1",
                        link = "link 1-1",
                        host = "host 1-1",
                        isSponsored = false,
                        type = EntryType.UNKNOWN
                    ),
                    IssueEntry(
                        title = "title 1-2",
                        description = "desc 1-2",
                        image = "image 1-2",
                        link = "link 1-2",
                        host = "host 1-2",
                        isSponsored = false,
                        type = EntryType.JOB
                    )
                ),
            "date 1"
            ),
            Issue(
                "Android Weekly 2",
                "link 2",
                listOf(
                    IssueEntry(
                        title = "title 2-1",
                        description = "desc 2-1",
                        image = "image 2-1",
                        link = "link 2-1",
                        host = "host 2-1",
                        isSponsored = true,
                        type = EntryType.SPONSORED
                    )
                ),
                "date 2"
            )
        )

        whenever(api.getIssues()).thenReturn(issues)

        val androidWeeklyJson = Factory.androidWeeklyJson(api, Factory.moshi())

        assertEquals(expected, androidWeeklyJson)
    }
}
