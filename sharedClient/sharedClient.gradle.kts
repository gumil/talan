plugins {
    SharedClient.plugins.forEach { (n, version) ->
        version?.let { id(n) version it } ?: id(n)
    }
}

apply<plugin.MultiplatformConfigurationPlugin>()

kotlin {
    sourceSets {
        @Suppress("UNUSED_VARIABLE")
        val commonMain by getting {
            dependencies {
                SharedClient.commonMain.forEach(::implementation)
                SharedClient.commonMainApi.forEach(::api)
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val commonTest by getting {
            dependencies {
                SharedClient.commonTest.forEach(::implementation)
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val androidMain by getting {
            dependencies {
                SharedClient.androidMain.forEach(::implementation)
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val androidTest by getting {
            dependencies {
                SharedClient.androidTest.forEach(::implementation)
            }
        }

        sourceSets["iosMain"].dependencies {
            SharedClient.nativeMain.forEach(::implementation)
        }
    }
}

sqldelight {
    database("talandb") {
        packageName = "dev.gumil.talan"
    }
}
