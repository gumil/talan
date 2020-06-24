internal object Versions {
    const val kotlin = "1.3.72"
    const val jacoco = "0.8.5"
    const val mockito = "2.2.0"
    const val coroutines = "1.3.7"
}

internal object Plugins {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

internal object Common {
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito}"
}

internal object Coroutines {
    const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutines}"
    const val jvm = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val js = "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.coroutines}"
    const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutines}"
}
