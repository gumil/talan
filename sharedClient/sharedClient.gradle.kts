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
                SharedClient.commonMain.forEach { implementation(it) }
                SharedClient.commonMainApi.forEach { api(it) }
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

        sourceSets["iosMain"].dependencies {
            SharedClient.nativeMain.forEach{ implementation(it) }
        }
    }
}

sqldelight {
    database("talandb") {
        packageName = "dev.gumil.talan"
    }
}
