package dev.gumil.talan.network.androidweekly.list

import dev.gumil.talan.androidweekly.list.AndroidWeeklyViewModel
import dev.gumil.talan.androidweekly.list.IssueListAction
import dev.gumil.talan.androidweekly.list.IssueListState
import dev.gumil.talan.network.NativeDispatcherProvider
import dev.gumil.talan.network.NetworkModule
import dev.gumil.talan.util.ViewModel

object AWListModule {
    fun provideViewModel(): ViewModel<IssueListAction, IssueListState> {
        return AndroidWeeklyViewModel(
            NetworkModule.provideTalanApi(),
            NativeDispatcherProvider()
        )
    }
}
