package dev.gumil.talan.integration

import dev.gumil.talan.TestDispatcherProvider
import dev.gumil.talan.di.AppComponent
import dev.gumil.talan.integration.fake.FakeTalanApi
import dev.gumil.talan.network.TalanApi
import dev.gumil.talan.util.DispatcherProvider

class TestAppComponent: AppComponent {
    override val talanApi: TalanApi by lazy {
        FakeTalanApi()
    }
    override val dispatcherProvider: DispatcherProvider by lazy {
        TestDispatcherProvider()
    }
}
