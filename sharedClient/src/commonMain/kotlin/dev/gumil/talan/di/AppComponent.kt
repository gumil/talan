package dev.gumil.talan.di

import dev.gumil.talan.network.NetworkModule
import dev.gumil.talan.network.TalanApi
import dev.gumil.talan.util.DefaultDispatcherProvider
import dev.gumil.talan.util.DispatcherProvider

interface AppComponent {
    val talanApi: TalanApi
    val dispatcherProvider: DispatcherProvider
}

class MainAppComponent: AppComponent {
    override val talanApi: TalanApi by lazy {
        NetworkModule.provideTalanApi()
    }

    override val dispatcherProvider: DispatcherProvider by lazy {
        DefaultDispatcherProvider()
    }
}