package dev.gumil.talan.androidweekly.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.instancekeeper.getOrCreate
import com.arkivanov.decompose.statekeeper.consume
import com.arkivanov.decompose.value.Value
import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.di.AppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
internal class AWListContainer(
    componentContext: ComponentContext,
    appComponent: AppComponent
): AWList, AWList.Events,  ComponentContext by componentContext {

    private val viewModel =
        instanceKeeper.getOrCreate {
            val state: IssueListState = stateKeeper.consume(KEY_STATE) ?: IssueListState.Screen()
            AndroidWeeklyViewModel(
                appComponent.talanApi,
                appComponent.dispatcherProvider,
                state
            )
        }

    override val model: AWList.Model =
        object : AWList.Model, AWList.Events by this {
            override val state: Value<IssueListState>
                get() = viewModel.state
        }

    override fun onItemClicked(issueEntry: IssueEntryUi) {
        viewModel.onItemClick(issueEntry)
    }

    private companion object {
        private const val KEY_STATE = "STATE"
    }
}
