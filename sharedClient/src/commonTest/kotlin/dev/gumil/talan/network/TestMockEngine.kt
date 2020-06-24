package dev.gumil.talan.network

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

internal class TestMockEngine {
    private lateinit var mockResponse: MockResponse

    fun enqueueResponseFromFile(
        payload: String,
        statusCode: HttpStatusCode = HttpStatusCode.OK
    ) {
        mockResponse = MockResponse(payload, statusCode)
    }

    fun get() = MockEngine {
        respond(
            mockResponse.body,
            mockResponse.statusCode,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }

    private data class MockResponse(
        val body: String,
        val statusCode: HttpStatusCode
    )
}
