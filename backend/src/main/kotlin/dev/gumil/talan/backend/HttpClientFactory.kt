package dev.gumil.talan.backend

import io.ktor.client.HttpClient

object HttpClientFactory {
    fun httpClient(): HttpClient {
        return HttpClient()
    }
}
