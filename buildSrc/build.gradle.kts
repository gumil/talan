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
    implementation("com.android.tools.build:gradle:4.2.0-alpha08")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.0")
    implementation("com.squareup.sqldelight:gradle-plugin:1.4.1")
    implementation(gradleApi())
}
