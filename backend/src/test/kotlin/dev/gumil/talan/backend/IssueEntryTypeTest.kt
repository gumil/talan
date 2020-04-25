package dev.gumil.talan.backend

import org.junit.Test
import kotlin.random.Random
import kotlin.test.assertEquals

internal class IssueEntryTypeTest {

    @Test
    fun `patreon maps to IssueType_PATREON`() {
        val title = "Patreon"
        val actual = EntryType.from(title)
        assertEquals(EntryType.PATREON, actual)
    }

    @Test
    fun `articles & tutorials maps to IssueType_ARTICLE`() {
        val title = "Articles & Tutorials"
        val actual = EntryType.from(title)
        assertEquals(EntryType.ARTICLE, actual)
    }

    @Test
    fun `libraries & code maps to IssueType_LIBRARY`() {
        val title = "Libraries & Code"
        val actual = EntryType.from(title)
        assertEquals(EntryType.LIBRARY, actual)
    }

    @Test
    fun `jobs maps to IssueType_JOB`() {
        val title = "Jobs"
        val actual = EntryType.from(title)
        assertEquals(EntryType.JOB, actual)
    }

    @Test
    fun `videos & podcasts maps to IssueType_VIDEO`() {
        val title = "Videos & Podcasts"
        val actual = EntryType.from(title)
        assertEquals(EntryType.VIDEO, actual)
    }

    @Test
    fun `sponsored maps to IssueType_SPONSORED`() {
        val title = "Sponsored"
        val actual = EntryType.from(title)
        assertEquals(EntryType.SPONSORED, actual)
    }

    @Test
    fun `random string maps to IssueType_UNKNOWN`() {
        val title = Random.nextDouble().toString()
        val actual = EntryType.from(title)
        assertEquals(EntryType.UNKNOWN, actual)
    }
}
