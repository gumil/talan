package dev.gumil.talan.androidweekly.list

import dev.gumil.talan.network.EntryType
import dev.gumil.talan.network.FakeTalanApi
import dev.gumil.talan.network.IssueEntry
import dev.gumil.talan.runTest
import dev.gumil.talan.util.TestDispatcherProvider
import dev.gumil.talan.util.Verifier
import dev.gumil.talan.util.verifyCollect
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.yield
import kotlin.random.Random
import kotlin.test.Test

@FlowPreview
@ExperimentalCoroutinesApi
internal class AndroidWeeklyViewModelTest {

    private val api = FakeTalanApi()

    private val viewModel = AndroidWeeklyViewModel(api, TestDispatcherProvider())

    private val issues = listOf(
        IssueEntry(
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
    fun actionRefresh() = runTest {
        val verifier = Verifier<IssueListState>()
        val job = viewModel.state
            .take(3)
            .verifyCollect(this, verifier.function)

        viewModel.dispatch(IssueListAction.Refresh)
        job.join()

        verifier.verifyOrder {
            verify(IssueListState.Screen())
            verify(IssueListState.Screen(emptyList(), IssueListState.Mode.LOADING))
            verify(IssueListState.Screen(issues, IssueListState.Mode.IDLE))
        }
    }

    @Test
    fun actionOnItemClick() = runTest {
        val verifier = Verifier<IssueListState>()
        val job = viewModel.state
            .take(2)
            .verifyCollect(this, verifier.function)

        yield() // Consume initial state

        val issue = IssueEntry(
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextBoolean(),
            EntryType.ARTICLE
        )

        viewModel.dispatch(IssueListAction.OnItemClick(issue))
        job.join()

        verifier.verifyOrder {
            verify(IssueListState.Screen())
            verify(IssueListState.GoToDetail(issue))
        }
    }
}
