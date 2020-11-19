object App {
    /**
     * This is currently being used in app.gradle.kts
     */
    @field:Suppress("MemberVisibilityCanBePrivate")
    const val compose = "1.0.0-alpha07"

    val plugins = listOf(
        "com.android.application",
        "org.jetbrains.kotlin.android",
        "org.jetbrains.kotlin.android.extensions"
    )

    val implementations = listOf(
        Decompose.core,
        Decompose.compose,
        "androidx.appcompat:appcompat:1.2.0",
        "androidx.compose.ui:ui:$compose",
        "androidx.compose.foundation:foundation-layout:$compose",
        "androidx.compose.material:material:$compose",
        "androidx.ui:ui-tooling:$compose",
        "androidx.browser:browser:1.2.0"
    )
    val testImplementations = listOf(
        "junit:junit:4.13",
        Coroutines.test
    )
    val androidTestImplementation = listOf(
        "androidx.test.ext:junit:1.1.2",
        "androidx.test.espresso:espresso-core:3.3.0"
    )
}
