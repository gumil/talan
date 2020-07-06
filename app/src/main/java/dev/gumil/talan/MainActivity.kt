package dev.gumil.talan

import com.nhaarman.acorn.android.navigation.AbstractNavigatorProvider
import com.nhaarman.acorn.android.navigation.NavigatorProvider
import com.nhaarman.acorn.state.NavigatorState
import dev.gumil.talan.acorn.AcornComposeActivity
import dev.gumil.talan.acorn.ComposeContainerFactory

internal class MainActivity : AcornComposeActivity() {
    override fun provideNavigatorProvider(): NavigatorProvider {
        return object : AbstractNavigatorProvider<MainNavigator>() {
            override fun createNavigator(savedState: NavigatorState?): MainNavigator {
                return MainNavigator(savedState)
            }
        }
    }

    override fun provideComposeContainerFactory(): ComposeContainerFactory {
        return MainContainerFactory()
    }
}
