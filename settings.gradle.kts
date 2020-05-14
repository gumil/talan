include(":sharedClient")
include(":backend")
include(":app")
rootProject.name = "Talan"

rootProject.children.forEach {
    it.buildFileName = "${it.name}.gradle.kts"
}
