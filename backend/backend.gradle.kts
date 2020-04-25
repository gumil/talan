import io.kotless.plugin.gradle.dsl.kotless

plugins {
    kotlin("jvm")
    id("io.kotless") version "0.1.3"
}

group = "dev.gumil.talan"

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

kotless {
    extensions {
        local {
            useAWSEmulation = true
        }
    }
}

dependencies {
    implementation("io.kotless:lang:0.1.3")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.1")
}
