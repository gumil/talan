object SharedClient {

    val plugins = listOf(
        "org.jetbrains.kotlin.multiplatform" to null,
        Serialization.plugin,
        "com.android.library" to null,
        "com.squareup.sqldelight" to null,
        "kotlin-android-extensions" to null
    )

    val commonMain = listOf(
        Serialization.json,
        // Coroutines is added to directly to exclude transitive dependencies
        // Coroutines.core,
        Ktor.core,
        Ktor.json,
        Ktor.logging,
        Ktor.serialization,
        "co.touchlab:stately-common:1.1.1"
    )

    val commonApi = listOf(
        Decompose.core
    )

    val commonTest = listOf(
        "org.jetbrains.kotlin:kotlin-test-common",
        "org.jetbrains.kotlin:kotlin-test-annotations-common",
        Ktor.test
    )

    val androidMain = listOf(
        "org.slf4j:slf4j-simple:1.7.30",
        Ktor.android
    )

    val androidTest = listOf(
        "org.jetbrains.kotlin:kotlin-test-junit"
    )

    val nativeMain = listOf(
        Ktor.ios
    )
}
