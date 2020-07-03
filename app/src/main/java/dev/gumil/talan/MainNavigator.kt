package dev.gumil.talan

import com.nhaarman.acorn.navigation.StackNavigator
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.NavigatorState
import com.nhaarman.acorn.state.SceneState
import kotlin.reflect.KClass

internal class MainNavigator(
    savedState: NavigatorState?
): StackNavigator(savedState), TestSceneOne.Events {
    override fun initialStack(): List<Scene<out Container>> {
        return listOf(TestSceneOne(this))
    }

    override fun instantiateScene(
        sceneClass: KClass<out Scene<*>>,
        state: SceneState?
    ): Scene<out Container> {
        return when(sceneClass) {
            TestSceneOne::class -> TestSceneOne(this)
            TestSceneTwo::class -> TestSceneTwo()
            else -> error("Unknown scene class: $sceneClass")
        }
    }

    override fun onClickButton() {
        push(TestSceneTwo())
    }
}
