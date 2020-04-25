package dev.gumil.talan.backend

interface AndroidWeeklyApi {
    suspend fun getIssues(): List<Issue>
}
