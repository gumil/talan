package dev.gumil.talan.network

interface TalanApi {
    suspend fun getAndroidWeeklyIssues(): List<Issue>
}
