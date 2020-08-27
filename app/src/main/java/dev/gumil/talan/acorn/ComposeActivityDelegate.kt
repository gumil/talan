package dev.gumil.talan.acorn

import android.app.Activity
import android.os.Bundle
import androidx.compose.runtime.MutableState
import com.nhaarman.acorn.OnBackPressListener
import com.nhaarman.acorn.android.navigation.NavigatorProvider
import com.nhaarman.acorn.android.util.toBundle
import com.nhaarman.acorn.android.util.toNavigatorState
import com.nhaarman.acorn.navigation.DisposableHandle
import com.nhaarman.acorn.navigation.Navigator
import com.nhaarman.acorn.navigation.TransitionData
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.NavigatorState

internal class ComposeActivityDelegate(
    private val activity: Activity,
    private val currentScreen: MutableState<ComposeContainer>,
    private val navigatorProvider: NavigatorProvider,
    private val composeContainerFactory: ComposeContainerFactory
): Navigator.Events {

    private lateinit var navigator: Navigator

    private var disposable: DisposableHandle? = null
        set(value) {
            field?.dispose()
            field = value
        }

    fun onCreate(savedInstanceState: Bundle?) {
        navigator = navigatorProvider.navigatorFor(savedInstanceState.navigatorState)
        disposable = navigator.addNavigatorEventsListener(this)
    }

    fun onStart() {
        navigator.onStart()
    }

    fun onBackPressed(): Boolean {
        return (navigator as? OnBackPressListener)?.onBackPressed() ?: false
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.navigatorState = navigatorProvider.saveNavigatorState()
    }

    companion object {
        private var Bundle?.navigatorState: NavigatorState?
            get() = this?.getBundle("navigator")?.toNavigatorState()
            set(value) {
                this?.putBundle("navigator", value?.toBundle())
            }
    }

    override fun finished() {
        activity.finish()
    }

    @Suppress("UNCHECKED_CAST")
    override fun scene(scene: Scene<out Container>, data: TransitionData?) {
        val container = composeContainerFactory.composeContainerFor(scene)
        (scene as Scene<Container>).attach(container)
        currentScreen.value = container
    }
}
