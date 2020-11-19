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
    val kotlinVersion = "1.4.10"
    implementation("com.android.tools.build:gradle:4.2.0-alpha15")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("com.squareup.sqldelight:gradle-plugin:1.4.4")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.36.0")
    implementation(gradleApi())
}
