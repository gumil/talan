package dev.gumil.talan.acorn

import androidx.compose.runtime.Composable
import com.nhaarman.acorn.presentation.Container

interface ComposeContainer: Container {
    @Composable
    fun render()
}

internal class Empty: ComposeContainer {
    @Composable
    override fun render() {
        // do nothing. an empty screen.
    }
}
