plugins {
    App.plugins.forEach { id(it) }
}

apply<plugin.AndroidConfigurationPlugin>()

android {
    defaultConfig {
        applicationId = "dev.gumil.talan"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), file("proguard-rules.pro"))
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf(
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview",
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xallow-jvm-ir-dependencies",
            "-Xskip-prerelease-check"
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = App.compose
        kotlinCompilerVersion = "1.4.0"
    }
    packagingOptions {
        pickFirst("META-INF/ktor-client-serialization.kotlin_module")
        pickFirst("META-INF/ktor-client-json.kotlin_module")
        pickFirst("META-INF/ktor-client-core.kotlin_module")
        pickFirst("META-INF/ktor-client-logging.kotlin_module")
        pickFirst("META-INF/ktor-io.kotlin_module")
        pickFirst("META-INF/ktor-utils.kotlin_module")
        pickFirst("META-INF/ktor-http.kotlin_module")
        pickFirst("META-INF/ktor-http-cio.kotlin_module")
        pickFirst("META-INF/kotlinx-serialization-runtime.kotlin_module")
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(project(":sharedClient"))
    App.implementations.forEach{ implementation(it) }
    App.testImplementations.forEach{ testImplementation(it) }
    App.androidTestImplementation.forEach{ androidTestImplementation(it) }
}
