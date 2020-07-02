plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId = "dev.gumil.talan"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), file("proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "0.1.0-dev14"
        kotlinCompilerVersion = "1.3.70-dev-withExperimentalGoogleExtensions-20200424"
    }
}

dependencies {
    implementation(project(":sharedClient"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
    implementation("dev.gumil.kaskade:core:0.4.0")
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("com.google.android.material:material:1.1.0")
    implementation("androidx.ui:ui-core:0.1.0-dev14")
    implementation("androidx.ui:ui-layout:0.1.0-dev14")
    implementation("androidx.ui:ui-material:0.1.0-dev14")
    implementation("androidx.ui:ui-tooling:0.1.0-dev14")
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
