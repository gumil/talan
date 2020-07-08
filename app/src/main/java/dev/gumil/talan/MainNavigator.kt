package dev.gumil.talan

import com.nhaarman.acorn.navigation.SavableNavigator
import com.nhaarman.acorn.navigation.StackNavigator
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.NavigatorState
import com.nhaarman.acorn.state.SceneState
import dev.gumil.talan.androidweekly.list.AWListScene
import dev.gumil.talan.di.AppComponent
import kotlin.reflect.KClass

internal class MainNavigator(
    savedState: NavigatorState?,
    private val appComponent: AppComponent
): StackNavigator(savedState), SavableNavigator {

    override fun initialStack(): List<Scene<out Container>> {
        return listOf(AWListScene.newInstance(
            appComponent.talanApi,
            appComponent.dispatcherProvider
        ))
    }

    override fun instantiateScene(
        sceneClass: KClass<out Scene<*>>,
        state: SceneState?
    ): Scene<out Container> {
        return when(sceneClass) {
            AWListScene::class -> AWListScene.newInstance(
                appComponent.talanApi,
                appComponent.dispatcherProvider,
                state
            )
            else -> error("Unknown scene class: $sceneClass")
        }
    }
}
