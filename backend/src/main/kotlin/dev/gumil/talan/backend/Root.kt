package dev.gumil.talan.backend

import com.squareup.moshi.Moshi
import io.kotless.MimeType
import io.kotless.dsl.lang.http.Get

private val androidWeeklyApi = Factory.androidWeeklyApi()
private val moshi = Moshi.Builder().build()

@Get("/", MimeType.JSON)
fun gettingStartedPage(): String {
    val issues = androidWeeklyApi.getIssues()
    val jsonAdapter = moshi.adapter(Response::class.java)
    return jsonAdapter.toJson(Response(issues))
}
