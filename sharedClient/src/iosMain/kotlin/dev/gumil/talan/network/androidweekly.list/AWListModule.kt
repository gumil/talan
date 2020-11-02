package dev.gumil.talan.network.androidweekly.list

import dev.gumil.talan.androidweekly.list.AndroidWeeklyViewModel
import dev.gumil.talan.network.NativeDispatcherProvider
import dev.gumil.talan.network.NetworkModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

object AWListModule {

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun provideViewModel(): AndroidWeeklyViewModel {
        return AndroidWeeklyViewModel(
            NetworkModule.provideTalanApi(),
            NativeDispatcherProvider()
        )
    }
}
