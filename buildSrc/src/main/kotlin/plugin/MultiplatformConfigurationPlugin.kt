package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import java.io.File

class MultiplatformConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        configureMpp(project)
        project.configureMppTest()
        project.configureJacocoMultiplatform()
    }

    private fun configureMpp(project: Project) {
        project.configurations.create("compileClasspath")

        project.extensions.getByType<KotlinMultiplatformExtension>().run {
            jvm()

            val iOSTargetConfiguration: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
                if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
                    ::iosArm64
                else
                    ::iosX64

            val iOSTarget = iOSTargetConfiguration("ios") {
                binaries {
                    framework {
                        baseName = "SharedClient"
                    }
                }
            }
            project.configurePackForXcode(iOSTarget)
        }
    }

    private fun Project.configurePackForXcode(target: KotlinNativeTarget) {
        val packForXcode = "packForXcode"
        tasks.register<Sync>(packForXcode) {
            group = "build"

            //selecting the right configuration for the iOS framework depending on the Xcode environment variables
            val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
            val framework = target.binaries.getFramework(mode)

            inputs.property("mode", mode)
            dependsOn(framework.linkTask)

            val targetDir = File(buildDir, "xcode-frameworks")
            from({ framework.outputDirectory })
            into(targetDir)

            doLast {
                val gradlew = File(targetDir, "gradlew")
                gradlew.writeText("#!/bin/bash\nexport 'JAVA_HOME=${System.getProperty("java.home")}'\ncd '${rootProject.rootDir}'\n./gradlew \$@\n")
                gradlew.setExecutable(true)
            }
        }

        tasks.getByName("build").dependsOn(packForXcode)
    }
}
