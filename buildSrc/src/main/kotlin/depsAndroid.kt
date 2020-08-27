object App {
    private const val compose = "1.0.0-alpha01"
    private const val acorn = "1.2.4"

    val plugins = listOf(
        "com.android.application",
        "org.jetbrains.kotlin.android",
         "org.jetbrains.kotlin.android.extensions"
    )

    val implementations = listOf(
        "androidx.appcompat:appcompat:1.2.0",
        "androidx.compose.ui:ui:$compose",
        "androidx.compose.foundation:foundation-layout:$compose",
        "androidx.compose.material:material:$compose",
        "androidx.ui:ui-tooling:$compose",
        "com.nhaarman.acorn.ext:acorn-android:$acorn",
        "com.nhaarman.acorn.ext:acorn-testing:$acorn"
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
