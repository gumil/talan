package dev.gumil.talan.network

import dev.gumil.talan.runTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TalanApiImplTest {

    private val testMockEngine = TestMockEngine()
    private val httpClient = NetworkModule.provideHttpClient(testMockEngine.get())
    private val api = TalanApiImpl(httpClient)

    @Test
    @Ignore
    // Does not work on iOS throws InvalidMutabilityException
    // https://youtrack.jetbrains.com/issue/KTOR-496
    fun getAndroidWeeklyIssues_returns_list_of_issues() = runTest {
        testMockEngine.enqueueResponseFromFile(androidWeeklyPayload)
        val expected = androidWeeklyIssues

        val actual = api.getAndroidWeeklyIssues()

        assertEquals(expected, actual)
    }
}
