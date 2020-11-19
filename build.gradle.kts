// Top-level build file where you can add configuration options common to all sub-projects/modules.
allprojects {
    apply<plugin.DependencyVersionsPlugin>()
    repositories {
        maven ("https://dl.bintray.com/kotlin/kotlin-eap")
        maven ("https://kotlin.bintray.com/kotlinx")
        maven("https://dl.bintray.com/arkivanov/maven")
        google()
        jcenter()
        mavenLocal()
    }
}

val cleanAll by tasks.creating(Delete::class) {
    delete(rootProject.buildDir)
}
