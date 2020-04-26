package dev.gumil.talan.backend

interface AndroidWeeklyApi {
    fun getIssues(): List<Issue>
}
