object PluginVersions {
    const val kotlin = "1.3.72"
    const val android = "4.1.0-alpha09"
}

object Plugins {
    const val android = "com.android.tools.build:gradle:${PluginVersions.android}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlin}"
}
