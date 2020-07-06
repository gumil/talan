package dev.gumil.talan

import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import dev.gumil.talan.acorn.ComposeContainer
import dev.gumil.talan.acorn.ComposeContainerFactory
import dev.gumil.talan.androidweekly.list.AWListScene
import dev.gumil.talan.androidweekly.list.AWListViewContainer

internal class MainContainerFactory: ComposeContainerFactory {
    override fun composeContainerFor(scene: Scene<out Container>): ComposeContainer {
        return when (scene.key.value) {
            AWListScene::class.java.name -> AWListViewContainer()
            else -> error("Not supported")
        }
    }
}
