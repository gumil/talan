package dev.gumil.talan.backend

import okhttp3.OkHttpClient

object Factory {
    fun httpClient(): OkHttpClient {
        return OkHttpClient()
    }

    fun androidWeeklyApi(): AndroidWeeklyApi {
        return AndroidWeeklyApiImpl(httpClient())
    }
}
