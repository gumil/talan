import io.kotless.plugin.gradle.dsl.kotless

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("io.kotless") version "0.1.3"
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
    implementation("io.kotless:lang:0.1.3")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.1")
    implementation("com.squareup.okhttp3:okhttp:4.5.0")
    implementation("com.squareup.moshi:moshi:1.9.2")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.9.2")
    implementation("org.jsoup:jsoup:1.13.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.3.72")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.5.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

}
