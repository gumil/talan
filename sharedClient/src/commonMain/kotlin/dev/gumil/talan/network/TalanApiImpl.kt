package dev.gumil.talan.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class TalanApiImpl(
    private val httpClient: HttpClient
): TalanApi {

    private val url = "https://nl5zusucx5.execute-api.eu-central-1.amazonaws.com/1/androidweekly"

    override suspend fun getAndroidWeeklyIssues(): List<Issue> {
        return httpClient.get<Response>(url).issues
    }
}
