package dev.gumil.talan

import com.nhaarman.acorn.android.navigation.NavigatorProvider
import dev.gumil.talan.acorn.AcornComposeActivity
import dev.gumil.talan.acorn.ComposeContainerFactory

internal class MainActivity : AcornComposeActivity() {

    override fun provideNavigatorProvider(): NavigatorProvider {
        return (application as TalanApp).navigatorProvider
    }

    override fun provideComposeContainerFactory(): ComposeContainerFactory {
        return MainContainerFactory()
    }
}
