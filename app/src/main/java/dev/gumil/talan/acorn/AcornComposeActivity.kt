package dev.gumil.talan.acorn

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.mutableStateOf
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import com.nhaarman.acorn.android.navigation.NavigatorProvider

internal abstract class AcornComposeActivity: AppCompatActivity() {
    protected abstract fun provideNavigatorProvider(): NavigatorProvider
    protected abstract fun provideComposeContainerFactory(): ComposeContainerFactory

    private val navigatorProvider: NavigatorProvider by lazy {
        provideNavigatorProvider()
    }

    private val composeContainerFactory: ComposeContainerFactory by lazy {
        provideComposeContainerFactory()
    }

    private val currentScreen = mutableStateOf<ComposeContainer>(Empty())

    private val acornDelegate: ComposeActivityDelegate by lazy {
        ComposeActivityDelegate(
            this,
            currentScreen,
            navigatorProvider,
            composeContainerFactory
        )
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        acornDelegate.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Crossfade(currentScreen.value) { screen ->
                    screen.render()
                }
            }
        }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        acornDelegate.onStart()
    }

    @CallSuper
    override fun onBackPressed() {
        if (!acornDelegate.onBackPressed()) {
            super.onBackPressed()
        }
    }

    /**
     * [AppCompatActivity.onSaveInstanceState] saves the view hierarchy state,
     * which is something we do manually. Therefore we do not call the super
     * implementation.
     */
    @SuppressLint("MissingSuperCall")
    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        acornDelegate.onSaveInstanceState(outState)
    }
}
