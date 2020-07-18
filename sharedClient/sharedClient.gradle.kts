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

        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                SharedClient.jvmMain.forEach(::implementation)
            }
        }

        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                SharedClient.jvmTest.forEach(::implementation)
            }
        }

        sourceSets["iosMain"].dependencies {
            SharedClient.nativeMain.forEach(::implementation)
        }

        sourceSets["iosTest"].dependencies {
            SharedClient.nativeTest.forEach(::implementation)
        }
    }
}
