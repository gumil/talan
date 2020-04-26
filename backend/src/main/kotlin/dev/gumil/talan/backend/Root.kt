package dev.gumil.talan.backend

import io.kotless.MimeType
import io.kotless.dsl.lang.http.Get

private val androidWeeklyApi = Factory.androidWeeklyApi()
private val moshi = Factory.moshi()

@Get("/", MimeType.JSON)
fun root(): String = Factory.androidWeeklyJson(androidWeeklyApi, moshi)
