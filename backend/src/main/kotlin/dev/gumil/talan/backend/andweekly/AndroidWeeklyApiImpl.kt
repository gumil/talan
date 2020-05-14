package dev.gumil.talan.backend.andweekly

import dev.gumil.talan.backend.EntryType
import dev.gumil.talan.backend.Issue
import dev.gumil.talan.backend.IssueEntry
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.parser.Parser
import org.jsoup.select.Elements

private const val URL = "https://us2.campaign-archive.com/feed?u=887caf4f48db76fd91e20a06d&id=4eb677ad19"

internal class AndroidWeeklyApiImpl(
    private val httpClient: HttpClient
): AndroidWeeklyApi {
    override suspend fun getIssues(): List<Issue> {
        val httpResponse = httpClient.get<String>(URL)

        val doc = Jsoup.parse(httpResponse, "", Parser.xmlParser())
        return doc.child(0)
            .getElementsByTag("item")
            .map { item ->
                Issue(
                    title = item.getElementsByTag("title").first().text(),
                    link = item.getElementsByTag("link").first().text(),
                    entries = parseDescription(item.getElementsByTag("description").first().text()),
                    publishDate = item.getElementsByTag("pubDate").first().text()
                )
            }
    }

    private fun parseDescription(description: String): List<IssueEntry> {
        val document = Jsoup.parse(description)
        val items = document.getElementsByClass("wrapper")

        var issueType = EntryType.UNKNOWN
        var isSponsored = false
        return items
            .mapNotNull { item ->
                val title = item.getElementsByTag("h2")
                if (title.size > 0) {
                    issueType = EntryType.from(
                        title.first().text()
                    )
                    return@mapNotNull null
                }

                val headline = item.getElementsByClass("article-headline")
                if (headline.size > 0) {
                    parseIssueItem(item, headline, isSponsored, issueType).also {
                        isSponsored = false
                    }
                } else {
                    isSponsored = item.getElementsByTag("h5").firstOrNull() != null
                    null
                }
            }
    }

    private fun parseIssueItem(item: Element, headline: Elements, isSponsored: Boolean, entryType: EntryType): IssueEntry {
        val articleImage = item.getElementsByTag("img").firstOrNull()?.attr("src")
        val articleLink = headline.first().attr("href")
        val articleTitle = headline.first().text()
        val description = item.getElementsByTag("p").firstOrNull()?.text()

        return IssueEntry(
            title = articleTitle,
            description = description ?: "",
            image = articleImage ?: "",
            link = articleLink,
            host = articleLink,
            isSponsored = isSponsored,
            type = entryType
        )
    }
}
