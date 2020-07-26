package plugin

import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.IvyArtifactRepository
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithSimulatorTests
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

fun Project.configureMppTest() {
    configureIosTest(this)

    tasks.register("mppTest") {
        dependsOn("testDebugUnitTest", "iosTest")
        group = JavaBasePlugin.VERIFICATION_GROUP
    }
}

private fun configureIosTest(project: Project) {
    project.tasks.getByName("iosTest") {
        group = JavaBasePlugin.VERIFICATION_GROUP
        dependsOn("linkIos")
        val device = project.findProperty("iosDevice")?.toString() ?: "iPhone 8"

        doLast {
            val iosX64 = project.extensions
                .getByType<KotlinMultiplatformExtension>()
                .targets
                .filterIsInstance<KotlinNativeTargetWithSimulatorTests>()
                .first()
            val binary = iosX64.binaries.getTest(NativeBuildType.DEBUG).outputFile
            project.exec {
                commandLine("xcrun", "simctl", "spawn", device, binary.absolutePath)
            }
        }
    }
}
