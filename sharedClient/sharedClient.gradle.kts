import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    SharedClient.plugins.forEach { (n, version) ->
        version?.let { id(n) version it } ?: id(n)
    }
}

apply<plugin.MultiplatformConfigurationPlugin>()

android {
    (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions") {
        jvmTarget = "1.8"
    }
}

kotlin {
    sourceSets {
        @Suppress("UNUSED_VARIABLE")
        val commonMain by getting {
            dependencies {
                implementation(Coroutines.core) {
                    version { strictly(Versions.coroutines) }
                }
                SharedClient.commonMain.forEach { implementation(it) }
                SharedClient.commonApi.forEach { api(it) }
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val commonTest by getting {
            dependencies {
                SharedClient.commonTest.forEach { implementation(it) }
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val androidMain by getting {
            dependencies {
                SharedClient.androidMain.forEach{ implementation(it) }
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val androidTest by getting {
            dependencies {
                SharedClient.androidTest.forEach{ implementation(it) }
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val iosMain by getting {
            dependencies {
                SharedClient.nativeMain.forEach{ implementation(it) }
            }
        }
    }
}

sqldelight {
    database("talandb") {
        packageName = "dev.gumil.talan"
    }
}
