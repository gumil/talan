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

        js().compilations["main"].defaultSourceSet {
            dependencies {
                SharedClient.jsMain.forEach(::implementation)
            }
        }

        js().compilations["test"].defaultSourceSet {
            dependencies {
                SharedClient.jsTest.forEach(::implementation)
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
