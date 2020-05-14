import io.kotless.plugin.gradle.dsl.kotless

plugins {
    Backend.plugins.forEach { (n, version) ->
        version?.let { id(n) version it } ?: id(n)
    }
}

group = "dev.gumil.talan"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotless {
    config {
        bucket = "talaan"

        terraform {
            profile = "default"
            region = "eu-central-1"
        }
    }

    extensions {
        local {
            useAWSEmulation = true
        }
    }
}

dependencies {
    Backend.implementations.forEach(::implementation)
    Backend.testImplementations.forEach(::testImplementation)
}
