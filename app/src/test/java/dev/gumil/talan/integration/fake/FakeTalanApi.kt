package dev.gumil.talan.integration.fake

import dev.gumil.talan.network.EntryType
import dev.gumil.talan.network.Issue
import dev.gumil.talan.network.IssueEntry
import dev.gumil.talan.network.TalanApi
import kotlin.random.Random

class FakeTalanApi : TalanApi {
    override suspend fun getAndroidWeeklyIssues(): List<Issue> {
        return issues
    }

    companion object {
        val issues: List<Issue> by lazy {
            mutableListOf<Issue>().apply {
                repeat(5) { add(createIssue()) }
            }
        }

        private fun createIssue() = Issue(
            title = Random.nextInt().toString(),
            link = Random.nextInt().toString(),
            publishDate = Random.nextInt().toString(),
            entries = mutableListOf<IssueEntry>().apply {
                repeat(10) { add(createEntry()) }
            }
        )

        private fun createEntry() = IssueEntry(
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextBoolean(),
            EntryType.values()[Random.nextInt(EntryType.values().size)]
        )
    }
}
