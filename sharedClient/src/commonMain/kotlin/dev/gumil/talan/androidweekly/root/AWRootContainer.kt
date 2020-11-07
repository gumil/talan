package dev.gumil.talan.androidweekly.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.router
import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize
import com.arkivanov.decompose.value.Value
import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.androidweekly.list.AWList
import dev.gumil.talan.androidweekly.list.AWListContainer
import dev.gumil.talan.di.AppComponent
import dev.gumil.talan.util.UninitializedValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
internal class AWRootContainer(
    componentContext: ComponentContext,
    private val appComponent: AppComponent
) : AWRoot, AWRoot.Navigation, ComponentContext by componentContext {

    private val router =
        router<Configuration, AWRoot.Child>(
            initialConfiguration = Configuration.List,
            handleBackButton = true,
            componentFactory = ::resolveChild
        )

    private val navigateToWeb = UninitializedValue<IssueEntryUi>()

    override val model: AWRoot.Model = object : AWRoot.Model {
        override val routerState: Value<RouterState<*, AWRoot.Child>> = router.state
        override val navigateToWebView: Value<IssueEntryUi> = navigateToWeb
    }

    override fun navigateToDetail(issueEntryUi: IssueEntryUi) {
        navigateToWeb.setValue(issueEntryUi)
    }

    private fun resolveChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ): AWRoot.Child =
        when (configuration) {
            Configuration.List -> AWRoot.Child.List(list(componentContext).model)
        }

    private fun list(componentContext: ComponentContext): AWList =
        AWListContainer(componentContext, this, appComponent)

    private sealed class Configuration : Parcelable {
        @Parcelize
        object List : Configuration()
    }
}
