package dev.gumil.talan.androidweekly

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import dev.gumil.talan.androidweekly.root.AWRoot
import com.arkivanov.decompose.extensions.compose.children

@Composable
operator fun AWRoot.Model.invoke() {
    routerState.children { child, configuration ->
        Crossfade(currentChild = child, currentKey = configuration) { currentChild ->
            when (currentChild) {
                is AWRoot.Child.List -> currentChild.model()
            }
        }
    }
}

@Composable
fun <T> Crossfade(currentChild: T, currentKey: Any, children: @Composable (T) -> Unit) {
    Crossfade(current = ChildWrapper(currentChild, currentKey)) {
        children(it.child)
    }
}

private class ChildWrapper<out T>(val child: T, val key: Any) {
    override fun equals(other: Any?): Boolean = key == (other as? ChildWrapper<*>)?.key
    override fun hashCode(): Int = key.hashCode()
}
