object App {
    private const val compose = "0.1.0-dev14"
    private const val acorn = "1.2.4"

    val plugins = listOf(
        "com.android.application",
        "org.jetbrains.kotlin.android"
        // "org.jetbrains.kotlin.android.extensions",
        // Kotlin android extensiosn does not work well with Jetpack Compose
    )

    val implementations = listOf(
        "androidx.appcompat:appcompat:1.1.0",
        "androidx.ui:ui-core:$compose",
        "androidx.ui:ui-layout:$compose",
        "androidx.ui:ui-material:$compose",
        "androidx.ui:ui-tooling:$compose",
        "com.nhaarman.acorn.ext:acorn-android:$acorn",
        "com.nhaarman.acorn.ext:acorn-testing:$acorn"
    )
    val testImplementations = listOf(
        "junit:junit:4.13",
        Coroutines.test
    )
    val androidTestImplementation = listOf(
        "androidx.test.ext:junit:1.1.1",
        "androidx.test.espresso:espresso-core:3.2.0"
    )
}
