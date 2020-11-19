package plugin

import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.named
import java.util.Locale

class DependencyVersionsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.apply<VersionsPlugin>()

        project.tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
            resolutionStrategy {
                componentSelection {
                    all {
                        if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
                            reject("Release candidate")
                        }
                    }
                }
            }

            outputFormatter = "json"
            outputDir = "build/dependencyUpdates"
            reportfileName = "report"
        }
    }

    private fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
            version.toUpperCase(Locale.getDefault()).contains(it)
        }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }
}
