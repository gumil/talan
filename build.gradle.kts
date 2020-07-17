// Top-level build file where you can add configuration options common to all sub-projects/modules.
allprojects {
    repositories {
        google()
        jcenter()
        mavenLocal()
    }
}

val cleanAll by tasks.creating(Delete::class) {
    delete(rootProject.buildDir)
}
