package dev.gumil.talan.backend

import kotlinx.coroutines.runBlocking

fun main() {
    val androidWeeklyApi: AndroidWeeklyApi = AndroidWeeklyApiImpl(HttpClientFactory.httpClient())
    runBlocking {
        val issues = androidWeeklyApi.getIssues()
        println(issues)
    }
}