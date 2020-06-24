package dev.gumil.talan.network

import dev.gumil.talan.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TalanApiImplTest {

    private val testMockEngine = TestMockEngine()
    private val httpClient = NetworkModule.provideHttpClient(testMockEngine.get())
    private val api = TalanApiImpl(httpClient)

    @Test
    fun getAndroidWeeklyIssues_returns_list_of_issues() = runTest {
        testMockEngine.enqueueResponseFromFile(androidWeeklyPayload)
        val expected = androidWeeklyIssues

        val actual = api.getAndroidWeeklyIssues()

        assertEquals(expected, actual)
    }
}
