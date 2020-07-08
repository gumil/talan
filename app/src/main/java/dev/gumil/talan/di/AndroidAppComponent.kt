package dev.gumil.talan.di

import dev.gumil.talan.AndroidDispatcherProvider
import dev.gumil.talan.network.NetworkModule
import dev.gumil.talan.network.TalanApi
import dev.gumil.talan.util.DispatcherProvider

class AndroidAppComponent: AppComponent {
    override val talanApi: TalanApi by lazy {
        NetworkModule.provideTalanApi()
    }

    override val dispatcherProvider: DispatcherProvider by lazy {
        AndroidDispatcherProvider()
    }
}
