object SharedClient {
    val plugins = listOf(
        "org.jetbrains.kotlin.multiplatform" to null,
        Serialization.plugin,
        "com.android.library" to null,
        "com.squareup.sqldelight" to null
    )

    val commonMain = listOf(
        Serialization.json,
        Kaskade.coroutines,
        // Coroutines is added to directly to exclude transitive dependencies
        // Coroutines.core,
        Ktor.core,
        Ktor.json,
        Ktor.logging,
        Ktor.serialization
    )

    val commonMainApi = listOf(
        Kaskade.core
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
