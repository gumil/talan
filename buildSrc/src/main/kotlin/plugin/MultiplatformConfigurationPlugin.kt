package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

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
            js {
                project.configure(listOf(compilations["main"], compilations["test"])) {
                    project.tasks.getByName(compileKotlinTaskName) {
                        kotlinOptions {
                            metaInfo = true
                            sourceMap = true
                            sourceMapEmbedSources = "always"
                            moduleKind = "umd"
                        }
                    }
                }
            }

            val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
            if (onPhone) {
                iosArm64("ios")
            } else {
                iosX64("ios")
            }
        }
    }
}
