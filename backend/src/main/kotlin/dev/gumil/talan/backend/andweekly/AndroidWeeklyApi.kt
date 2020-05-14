package dev.gumil.talan.backend.andweekly

import dev.gumil.talan.backend.Issue

interface AndroidWeeklyApi {
    suspend fun getIssues(): List<Issue>
}
