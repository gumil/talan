package dev.gumil.talan.acorn

import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene

internal interface ComposeContainerFactory {
    fun composeContainerFor(scene: Scene<out Container>): ComposeContainer
}
