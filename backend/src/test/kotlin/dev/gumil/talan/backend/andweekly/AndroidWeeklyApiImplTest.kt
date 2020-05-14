package dev.gumil.talan.backend.andweekly

import dev.gumil.talan.backend.EntryType
import dev.gumil.talan.backend.IssueEntry
import dev.gumil.talan.backend.readFromFile
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

internal class AndroidWeeklyApiImplTest {

    private val client = HttpClient(MockEngine) {
        engine {
            addHandler {
                respond(readFromFile("test-payload.xml"))
            }
        }
    }

    private val api = AndroidWeeklyApiImpl(client)

    @Test
    fun `successfully get issues`() = runBlocking {
        val expected = listOf(
            IssueEntry(
                title = "Android Weekly on Patreon",
                description = "Your biggest support is to subscribe to our weekly Android development newsletter. But if you want to support us a little bit more, then take a look at our new Patreon page. In the next upcoming weeks, we'll post updates and introduce you to some new benefits for being a Patreeon supporter!",
                image = "https://androidweekly.net/system/images/5e9327/873c93bc382900491a/square_Bildschirmfoto_2020-04-12_um_16.36.44.png",
                link = "https://www.patreon.com/androidweekly",
                host = "https://www.patreon.com/androidweekly",
                isSponsored = false,
                type = EntryType.PATREON
            ),
            IssueEntry(
                title = "Adventures in Compose - The Doom fire effect",
                description = "Adam Bennett takes the fire effect from the iconic game Doom and implements it using Jetpack Compose.",
                image = "https://androidweekly.net/system/images/5e9bab/353c93bc382900b400/square_Bildschirmfoto_2020-04-19_um_12.44.44.png",
                link = "https://adambennett.dev/2020/04/adventures-in-compose-the-doom-fire-effect/",
                host = "https://adambennett.dev/2020/04/adventures-in-compose-the-doom-fire-effect/",
                isSponsored = false,
                type = EntryType.ARTICLE
            ),
            IssueEntry(
                title = "Why The Top Apps Rely on Instabug over Crashlytics",
                description = "Catch bugs as soon as they happen and know exactly why a crash occurred. With Instabug you will automatically receive device data, network logs, and reproduction steps with every bug and crash report. It only takes a line of code to integrate. See more detailed features comparison and try Instabug for free here.",
                image = "https://androidweekly.net/system/images/5e9c2a/223c93bc382900ba50/square_Instabug-Logo-v_color.png",
                link = "https://instabug.com/crashlytics-alternative?utm_source=androidweekly&utm_medium=newsletters&utm_campaign=androidweekly-newsletters-q220-april",
                host = "https://instabug.com/crashlytics-alternative?utm_source=androidweekly&utm_medium=newsletters&utm_campaign=androidweekly-newsletters-q220-april",
                isSponsored = true,
                type = EntryType.ARTICLE
            ),
            IssueEntry(
                title = "Lead Android Developer",
                description = "Pictarine is looking for their future Lead Android Developer. Our app is noted 4.8 on the Play Store with 220K ratings. In 2019 alone, we printed more than 50 million pictures in the U.S. Become the 18th member of our passionate team. Stack: Kotlin, Java, AndroidX, Glide, Retrofit, Gson, OkHttp",
                image = "",
                link = "https://www.linkedin.com/jobs/view/1792210202/",
                host = "https://www.linkedin.com/jobs/view/1792210202/",
                isSponsored = false,
                type = EntryType.JOB
            ),
            IssueEntry(
                title = "BLTaxi",
                description = "BL Taxi is a simple app for calling a taxi in the city Banja Luka built using modern Android development tools (100% Kotlin, Android Jetpack, CI pipeline and much more).",
                image = "https://androidweekly.net/system/images/5e99c0/ef3c93bc3829009a4a/square_Bildschirmfoto_2020-04-19_um_12.45.27.png",
                link = "https://github.com/VladimirWrites/BLTaxi",
                host = "https://github.com/VladimirWrites/BLTaxi",
                isSponsored = false,
                type = EntryType.LIBRARY
            ),
            IssueEntry(
                title = "How we've used Kotlin to build a Mobile Design App",
                description = "Rebecca Franks describes her experience working on a Kotlin codebase. She covers some of the features in Kotlin used the most, some features eagerly overused and some of the mistakes made along the way.",
                image = "https://androidweekly.net/system/images/5e9bbb/603c93bc382900b46a/square_Bildschirmfoto_2020-04-19_um_12.46.16.png",
                link = "https://www.youtube.com/watch?v=b3RXWiBxrz8&feature=youtu.be",
                host = "https://www.youtube.com/watch?v=b3RXWiBxrz8&feature=youtu.be",
                isSponsored = false,
                type = EntryType.VIDEO
            )
        )

        val issues = api.getIssues()
        val entries = issues.first().entries
        assertEquals(2, issues.size)
        assertEquals(expected, entries)
        assertEquals(27, issues[1].entries.size)

        val numberOfSponsoredTypes = issues[1].entries.filter {
            it.type == EntryType.SPONSORED
        }.size
        assertEquals(1, numberOfSponsoredTypes)
    }
}
