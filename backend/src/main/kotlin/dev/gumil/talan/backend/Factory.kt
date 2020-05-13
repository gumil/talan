package dev.gumil.talan.backend

import com.squareup.moshi.Moshi
import dev.gumil.talan.backend.andweekly.AndroidWeeklyApi
import dev.gumil.talan.backend.andweekly.AndroidWeeklyApiImpl
import okhttp3.OkHttpClient

object Factory {
    fun httpClient(): OkHttpClient {
        return OkHttpClient()
    }

    fun androidWeeklyApi(): AndroidWeeklyApi {
        return AndroidWeeklyApiImpl(httpClient())
    }

    fun moshi(): Moshi {
        return Moshi.Builder().build()
    }

    fun androidWeeklyJson(androidWeeklyApi: AndroidWeeklyApi, moshi: Moshi): String {
        val issues = androidWeeklyApi.getIssues()
        val jsonAdapter = moshi.adapter(Response::class.java)
        return jsonAdapter.toJson(Response(issues))
    }
}
