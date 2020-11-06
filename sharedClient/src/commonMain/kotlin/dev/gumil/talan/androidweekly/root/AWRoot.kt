package dev.gumil.talan.androidweekly.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.value.Value
import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.androidweekly.list.AWList
import dev.gumil.talan.di.AppComponent
import dev.gumil.talan.di.MainAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

interface AWRoot {

    val model: Model

    interface Model {
        val routerState: Value<RouterState<*, Child>>
    }

    sealed class Child {
        class List(val model: AWList.Model) : Child()
    }

    interface Navigation {
        fun navigateToDetail(issueEntryUi: IssueEntryUi)
    }
}

@Suppress("FunctionName")
@FlowPreview
@ExperimentalCoroutinesApi
fun AWRoot(
    componentContext: ComponentContext,
    appComponent: AppComponent = MainAppComponent()
): AWRoot = AWRootContainer(componentContext, appComponent)
