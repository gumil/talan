package plugin

import org.gradle.api.Project
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.getByType

private const val COMPILE_SDK_VERSION = 30
private const val MIN_SDK_VERSION = 23

fun Project.configureAndroid(srcPrefix: String = "") = this.extensions.getByType<BaseExtension>().run {
    compileSdkVersion(COMPILE_SDK_VERSION)
    defaultConfig {
        minSdkVersion(MIN_SDK_VERSION)
        targetSdkVersion(COMPILE_SDK_VERSION)
    }

    sourceSets {
        getByName("main").java.srcDir("src/${srcPrefix}Main/kotlin")
        getByName("test").java.srcDir("src/${srcPrefix}Test/kotlin")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
