internal object Versions {
    const val kotlin = "1.4.0-rc"
    const val jacoco = "0.8.5"
    const val mockito = "2.2.0"
    const val coroutines = "1.3.8-1.4.0-rc"
    const val kaskade = "0.4.1"
    const val serialization = "1.0-M1-1.4.0-rc"
    const val ktor = "1.3.2-1.4.0-rc"
}

internal object Plugins {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

internal object Common {
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito}"
}

internal object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}

internal object Kaskade {
    const val core = "dev.gumil.kaskade:core:${Versions.kaskade}"
    const val coroutines = "dev.gumil.kaskade:coroutines:${Versions.kaskade}"
}

internal object Serialization {
    val plugin = "org.jetbrains.kotlin.plugin.serialization" to Versions.kotlin
    const val core = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.serialization}"
}

internal object Ktor {
    const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"
    const val json = "io.ktor:ktor-client-json:${Versions.ktor}"
    const val logging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val serialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val test = "io.ktor:ktor-client-mock:${Versions.ktor}"
}
