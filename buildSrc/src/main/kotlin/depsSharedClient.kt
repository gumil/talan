object SharedClient {
    private const val serialization = "0.20.0"

    val plugins = listOf(
        "org.jetbrains.kotlin.multiplatform" to null,
        "org.jetbrains.kotlin.plugin.serialization" to Versions.kotlin
    )

    val commonMain = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-common",
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serialization"
    )

    val commonTest = listOf(
        "org.jetbrains.kotlin:kotlin-test-common",
        "org.jetbrains.kotlin:kotlin-test-annotations-common"
    )

    val jvmMain = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8",
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization"
    )

    val jvmTest = listOf(
        "org.jetbrains.kotlin:kotlin-test-junit"
    )

    val jsMain = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-js",
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$serialization"
    )

    val jsTest = listOf(
        "org.jetbrains.kotlin:kotlin-test-js"
    )

    val nativeMain = listOf(
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serialization"
    )
}
