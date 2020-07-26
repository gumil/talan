package plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.gradle.testing.jacoco.plugins.JacocoPlugin
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File

fun Project.configureJacocoAndroid(srcPrefix: String = "") {
    configureJacoco {
        tasks.register<JacocoReport>(this) {
            setDependsOn(setOf("testDebugUnitTest"))
            group = "verification"
            description = "Runs jacoco test report for android"

            val debugTree = fileTree("${project.buildDir}/tmp/kotlin-classes/debug")
            val mainSrc = "${project.projectDir}/src/${srcPrefix}main/kotlin"

            sourceDirectories.setFrom(files(mainSrc))
            classDirectories.setFrom(files(debugTree))

            executionData.setFrom(fileTree(buildDir).apply {
                setIncludes(setOf("jacoco/testDebugUnitTest.exec", "outputs/code-coverage/connected/*coverage.ec"))
            })
        }
    }
}

private fun Project.configureJacoco(configuration: String.() -> Unit) {
    apply<JacocoPlugin>()

    extensions.getByType(JacocoPluginExtension::class.java).toolVersion = Versions.jacoco

    val task = "jacocoTestReport"

    task.configuration()

    tasks.named<JacocoReport>(task).configure {
        reports.apply {
            xml.apply {
                isEnabled = true
                destination = File("${project.buildDir}/reports/jacocoTestReport.xml")
            }
            html.apply {
                isEnabled = true
                destination = File("${project.buildDir}/reports/jacoco")
            }
        }
    }
}
