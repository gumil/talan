package dev.gumil.talan

import android.app.Application
import com.nhaarman.acorn.android.navigation.AbstractNavigatorProvider
import com.nhaarman.acorn.state.NavigatorState
import dev.gumil.talan.di.AndroidAppComponent
import dev.gumil.talan.di.AppComponent

internal class TalanApp: Application() {

    val appComponent by lazy {
        AndroidAppComponent()
    }

    val navigatorProvider by lazy {
        MainNavigatorProvider(appComponent)
    }
}

internal class MainNavigatorProvider(
    private val appComponent: AppComponent
): AbstractNavigatorProvider<MainNavigator>() {
    override fun createNavigator(savedState: NavigatorState?): MainNavigator {
        return MainNavigator(savedState, appComponent)
    }
}
