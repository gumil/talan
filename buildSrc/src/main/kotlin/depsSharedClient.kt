object SharedClient {
    private const val serialization = "0.20.0"
    private const val ktor = "1.3.2"

    val plugins = listOf(
        "org.jetbrains.kotlin.multiplatform" to null,
        "org.jetbrains.kotlin.plugin.serialization" to Versions.kotlin,
        "com.squareup.sqldelight" to null
    )

    val commonMain = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-common",
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serialization",
        "io.ktor:ktor-client-core:$ktor",
        "io.ktor:ktor-client-json:$ktor",
        "io.ktor:ktor-client-logging:$ktor",
        "io.ktor:ktor-client-serialization:$ktor",
        Kaskade.coroutines,
        Coroutines.common
    )

    val commonMainApi = listOf(
        Kaskade.core
    )

    val commonTest = listOf(
        "org.jetbrains.kotlin:kotlin-test-common",
        "org.jetbrains.kotlin:kotlin-test-annotations-common",
        "io.ktor:ktor-client-mock:$ktor"
    )

    val jvmMain = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8",
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization",
        "io.ktor:ktor-client-android:$ktor",
        "io.ktor:ktor-client-json-jvm:$ktor",
        "io.ktor:ktor-client-logging-jvm:$ktor",
        "io.ktor:ktor-client-serialization-jvm:$ktor",
        "org.slf4j:slf4j-simple:1.7.30",
        Coroutines.jvm
    )

    val jvmTest = listOf(
        "org.jetbrains.kotlin:kotlin-test-junit",
        "io.ktor:ktor-client-mock-jvm:$ktor"
    )

    val nativeMain = listOf(
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serialization",
        "io.ktor:ktor-client-ios:$ktor",
        "io.ktor:ktor-client-json-native:$ktor",
        "io.ktor:ktor-client-logging-native:$ktor",
        "io.ktor:ktor-client-serialization-native:$ktor",
        Coroutines.native
    )

    val nativeTest = listOf(
        "io.ktor:ktor-client-mock-native:$ktor"
    )
}
