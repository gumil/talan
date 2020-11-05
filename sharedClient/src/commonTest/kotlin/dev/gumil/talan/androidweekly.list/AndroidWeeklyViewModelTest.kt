package dev.gumil.talan.androidweekly.list

import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.network.EntryType
import dev.gumil.talan.network.FakeTalanApi
import dev.gumil.talan.network.Issue
import dev.gumil.talan.network.TalanApi
import dev.gumil.talan.util.TestDispatcherProvider
import dev.gumil.talan.util.Verifier
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.js.JsName
import kotlin.test.Test

@FlowPreview
@ExperimentalCoroutinesApi
internal class AndroidWeeklyViewModelTest {

    private val api = FakeTalanApi()

    private val issues = listOf(
        IssueEntryUi(
            "title",
            "description",
            "image",
            "link",
            "hosted",
            false,
            EntryType.ARTICLE
        )
    )

    @Test
    @JsName("when_initialized_should_call_refresh")
    fun `when initialized should call refresh`() {
        val verifier = Verifier<AWList.Screen>()
        val viewModel = AndroidWeeklyViewModel(api, TestDispatcherProvider())

        viewModel.state.subscribe(verifier.function)

        verifier.verifyOrder {
            // initial call to refresh
            verify(AWList.Screen(issues, AWList.Mode.IDLE))
        }
    }

    @Test
    fun actionRefresh() {
        val verifier = Verifier<AWList.Screen>()
        val viewModel = AndroidWeeklyViewModel(api, TestDispatcherProvider())

        viewModel.state.subscribe(verifier.function)

        viewModel.refresh()

        verifier.verifyOrder {
            // initial call to refresh
            verify(AWList.Screen(issues, AWList.Mode.IDLE))

            // actual call to refresh
            verify(AWList.Screen(issues, AWList.Mode.LOADING))
            verify(AWList.Screen(issues, AWList.Mode.IDLE))
        }
    }

    @Test
    fun actionRefresh_throws_error() {
        val exception = RuntimeException()
        val viewModel = AndroidWeeklyViewModel(object : TalanApi {
            override suspend fun getAndroidWeeklyIssues(): List<Issue> {
                throw exception
            }
        }, TestDispatcherProvider())

        val verifier = Verifier<AWList.Screen>()
        viewModel.state.subscribe(verifier.function)

        verifier.verifyOrder {
            verify(AWList.Screen(loadingMode = AWList.Mode.IDLE, exception = exception))
        }
    }
}
