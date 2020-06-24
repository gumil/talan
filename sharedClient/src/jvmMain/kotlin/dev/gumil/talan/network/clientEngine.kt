package dev.gumil.talan.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual fun clientEngine(): HttpClientEngine {
    return Android.create {}
}