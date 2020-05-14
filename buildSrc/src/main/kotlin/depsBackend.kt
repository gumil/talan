/**
 * To not overcomplicate things with kotlinx serialization and ktor since it's not
 * working correctly with kotless.
 */
object Backend {
    private const val kotless = "0.1.3"
    private const val jsoup = "1.13.1"
    private const val okhttp = "4.6.0"
    private const val moshi = "1.9.2"

    val plugins = listOf(
        "org.jetbrains.kotlin.jvm" to null,
        "org.jetbrains.kotlin.kapt" to null,
        "io.kotless" to kotless
    )
    val implementations = listOf(
        "io.kotless:lang:${kotless}",
        "org.jsoup:jsoup:${jsoup}",
        "com.squareup.okhttp3:okhttp:${okhttp}",
        "com.squareup.moshi:moshi:${moshi}"
    )
    val kapt = listOf(
        "com.squareup.moshi:moshi-kotlin-codegen:${moshi}"
    )
    val testImplementations = listOf(
        "org.jetbrains.kotlin:kotlin-test-junit:${PluginVersions.kotlin}",
        "com.squareup.okhttp3:mockwebserver:${okhttp}",
        Common.mockito
    )
}
