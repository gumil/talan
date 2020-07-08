package dev.gumil.talan.integration

import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.testing.ContainerProvider
import dev.gumil.talan.androidweekly.list.AWListScene
import dev.gumil.talan.integration.awlist.TestAWListContainer

object TestContainerProvider : ContainerProvider {
    override fun containerFor(scene: Scene<*>): Container {
        return when (scene) {
            is AWListScene -> TestAWListContainer()
            else -> error("Unknown scene $scene")
        }
    }
}