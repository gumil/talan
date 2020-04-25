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
    implementation("io.ktor:ktor-client-cio:1.3.2")
    implementation("org.jsoup:jsoup:1.13.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.3.72")
    testImplementation("io.ktor:ktor-client-mock-jvm:1.3.2")
}
