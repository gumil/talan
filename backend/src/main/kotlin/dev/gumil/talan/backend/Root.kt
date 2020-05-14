package dev.gumil.talan.backend

import io.kotless.MimeType
import io.kotless.dsl.lang.http.Get
import kotlinx.coroutines.runBlocking

private val androidWeeklyApi = Factory.androidWeeklyApi()

@Get("/androidweekly", MimeType.JSON)
fun androidWeekly(): String = runBlocking { Factory.androidWeeklyJson(androidWeeklyApi) }
