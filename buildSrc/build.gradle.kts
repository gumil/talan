buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
    maven("https://plugins.gradle.org/m2/")
}

dependencies {
    implementation("com.android.tools.build:gradle:4.2.0-alpha02")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    implementation("com.github.node-gradle:gradle-node-plugin:2.2.3")
    implementation(gradleApi())
}
