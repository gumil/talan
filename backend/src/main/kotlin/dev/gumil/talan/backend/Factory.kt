package dev.gumil.talan.backend

import dev.gumil.talan.backend.andweekly.AndroidWeeklyApi
import dev.gumil.talan.backend.andweekly.AndroidWeeklyApiImpl
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

object Factory {
    fun httpClient(): HttpClient {
        return HttpClient()
    }

    fun androidWeeklyApi(): AndroidWeeklyApi {
        return AndroidWeeklyApiImpl(httpClient())
    }

    suspend fun androidWeeklyJson(androidWeeklyApi: AndroidWeeklyApi): String {
        val issues = androidWeeklyApi.getIssues()
        val serializer = Response.serializer()
        val json = Json(JsonConfiguration.Stable)
        return json.stringify(serializer, Response(issues))
    }
}
