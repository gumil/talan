package dev.gumil.talan.network

import dev.gumil.talan.runTest
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TalanApiImplTest {

    @Test
    // @Ignore
    // Does not work on iOS throws InvalidMutabilityException
    // https://youtrack.jetbrains.com/issue/KTOR-496
    fun getAndroidWeeklyIssues_returns_list_of_issues() = runTest {
        val testMockEngine = MockEngine {
            respond(
                androidWeeklyPayload,
                HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val httpClient = NetworkModule.provideHttpClient(testMockEngine)
        val api = TalanApiImpl(httpClient)
        val expected = androidWeeklyIssues

        val actual = api.getAndroidWeeklyIssues()

        assertEquals(expected, actual)
    }
}
