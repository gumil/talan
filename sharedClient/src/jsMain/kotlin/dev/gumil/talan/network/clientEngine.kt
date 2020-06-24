package dev.gumil.talan.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js

actual fun clientEngine(): HttpClientEngine {
    return Js.create {}
}
