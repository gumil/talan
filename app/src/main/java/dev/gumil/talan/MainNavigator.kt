package dev.gumil.talan

import com.nhaarman.acorn.navigation.StackNavigator
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.NavigatorState
import com.nhaarman.acorn.state.SceneState
import dev.gumil.talan.androidweekly.list.AWListScene
import kotlin.reflect.KClass

internal class MainNavigator(
    savedState: NavigatorState?
): StackNavigator(savedState) {

    private val dispatcherProvider = AndroidDispatcherProvider()

    override fun initialStack(): List<Scene<out Container>> {
        return listOf(AWListScene(null, dispatcherProvider))
    }

    override fun instantiateScene(
        sceneClass: KClass<out Scene<*>>,
        state: SceneState?
    ): Scene<out Container> {
        return when(sceneClass) {
            AWListScene::class -> AWListScene(state, dispatcherProvider)
            else -> error("Unknown scene class: $sceneClass")
        }
    }
}
