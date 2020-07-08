package dev.gumil.talan.di

import dev.gumil.talan.network.TalanApi
import dev.gumil.talan.util.DispatcherProvider

interface AppComponent {
    val talanApi: TalanApi
    val dispatcherProvider: DispatcherProvider
}
