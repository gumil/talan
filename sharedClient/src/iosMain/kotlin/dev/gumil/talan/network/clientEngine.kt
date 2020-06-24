package dev.gumil.talan.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.Ios

actual fun clientEngine(): HttpClientEngine {
    return Ios.create {}
}
