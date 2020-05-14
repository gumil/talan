/**
 * To not overcomplicate things with kotlinx serialization and ktor since it's not
 * working correctly with kotless.
 */
object Backend {
    private const val kotless = "0.1.5"
    private const val jsoup = "1.13.1"
    private const val ktor = "1.3.2"
    private const val serialization = "0.20.0"

    val plugins = listOf(
        "org.jetbrains.kotlin.jvm" to null,
        "org.jetbrains.kotlin.plugin.serialization" to Versions.kotlin,
        "io.kotless" to kotless
    )
    val implementations = listOf(
        "io.kotless:lang:$kotless",
        "org.jsoup:jsoup:$jsoup",
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization",
        "io.ktor:ktor-client-cio:$ktor"
    )
    val testImplementations = listOf(
        "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}",
        "io.ktor:ktor-client-mock-jvm:$ktor",
        Common.mockito
    )
}
