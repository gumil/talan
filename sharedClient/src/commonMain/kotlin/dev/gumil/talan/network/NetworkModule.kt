package dev.gumil.talan.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json

expect fun clientEngine(): HttpClientEngine

object NetworkModule {

    fun provideHttpClient(engine: HttpClientEngine = clientEngine()): HttpClient {
        return HttpClient(engine) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json {
                        encodeDefaults = false
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    fun provideTalanApi(): TalanApi {
        return TalanApiImpl(provideHttpClient())
    }
}
