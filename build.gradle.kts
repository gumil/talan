// Top-level build file where you can add configuration options common to all sub-projects/modules.
allprojects {
    repositories {
        maven ("https://dl.bintray.com/kotlin/kotlin-eap")
        maven ("https://kotlin.bintray.com/kotlinx")
        google()
        jcenter()
        mavenLocal()
    }
}

val cleanAll by tasks.creating(Delete::class) {
    delete(rootProject.buildDir)
}
