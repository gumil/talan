package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.configureAndroid()
        project.configureJacocoAndroid()
    }
}
