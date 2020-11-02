package dev.gumil.talan.androidweekly.list

import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.network.EntryType
import dev.gumil.talan.network.FakeTalanApi
import dev.gumil.talan.network.Issue
import dev.gumil.talan.network.TalanApi
import dev.gumil.talan.runTest
import dev.gumil.talan.util.TestDispatcherProvider
import dev.gumil.talan.util.Verifier
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.random.Random
import kotlin.test.Test

@FlowPreview
@ExperimentalCoroutinesApi
internal class AndroidWeeklyViewModelTest {

    private val api = FakeTalanApi()

    private val dispatcherProvider = TestDispatcherProvider()
    private val viewModel = AndroidWeeklyViewModel(api, dispatcherProvider)

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
    fun actionRefresh() {
        val verifier = Verifier<IssueListState>()
        val viewModel = AndroidWeeklyViewModel(api, TestDispatcherProvider())

        viewModel.state.subscribe(verifier.function)

        viewModel.refresh()

        verifier.verifyOrder {
            verify(IssueListState.Screen())
            verify(IssueListState.Screen(emptyList(), IssueListState.Mode.LOADING))
            verify(IssueListState.Screen(issues, IssueListState.Mode.IDLE))
        }
    }

    @Test
    fun actionOnItemClick() {
        val verifier = Verifier<IssueListState>()

        viewModel.state.subscribe(verifier.function)

        val issue = IssueEntryUi(
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextBoolean(),
            EntryType.ARTICLE
        )

        viewModel.onItemClick(issue)

        verifier.verifyOrder {
            verify(IssueListState.Screen())
            verify(IssueListState.GoToDetail(issue))
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

        val verifier = Verifier<IssueListState>()
        viewModel.state.subscribe(verifier.function)

        viewModel.refresh()

        verifier.verifyOrder {
            verify(IssueListState.Screen())
            verify(IssueListState.Screen(loadingMode = IssueListState.Mode.LOADING))
            verify(IssueListState.Screen(loadingMode = IssueListState.Mode.IDLE, exception = exception))
        }
    }
}
