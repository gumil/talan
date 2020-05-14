internal object PluginVersions {
    const val kotlin = "1.3.72"
}

internal object Versions {
    const val jacoco = "0.8.4"
    const val mockito = "2.2.0"
}

internal object Plugins {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlin}"
}

internal object Common {
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito}"
}
