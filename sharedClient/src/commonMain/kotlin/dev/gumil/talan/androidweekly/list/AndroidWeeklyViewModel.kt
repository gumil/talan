package dev.gumil.talan.androidweekly.list

import dev.gumil.kaskade.ActionState
import dev.gumil.kaskade.Kaskade
import dev.gumil.kaskade.coroutines.coroutines
import dev.gumil.kaskade.coroutines.stateDamFlow
import dev.gumil.talan.network.TalanApi
import dev.gumil.talan.util.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class AndroidWeeklyViewModel(
    private val talanApi: TalanApi,
    private val dispatcherProvider: DispatcherProvider,
    initialState: IssueListState = IssueListState.Screen()
) {

    private val job = Job()

    private val uiScope = CoroutineScope(dispatcherProvider.main() + job)

    private val kaskade = Kaskade.create<IssueListAction, IssueListState>(initialState) {
        coroutines(uiScope) {
            onFlow<IssueListAction.Refresh> {
                getLatestAndroidWeekly()
            }
        }

        on<IssueListAction.OnItemClick> {
            IssueListState.GoToDetail(action.issue)
        }
    }

    val state: StateFlow<IssueListState> get() = _state

    private val _state = kaskade.stateDamFlow()

    fun dispatch(action: IssueListAction) {
        kaskade.dispatch(action)
    }

    fun clear() {
        kaskade.unsubscribe()
        _state.clear()
        job.cancel()
    }

    private suspend fun <A : IssueListAction> Flow<ActionState<A, IssueListState>>.getLatestAndroidWeekly(): Flow<IssueListState> {
        return this
            .map { it.currentState as IssueListState.Screen }
            .flatMapConcat { state ->
                flowOf(
                    state.copy(loadingMode = IssueListState.Mode.LOADING),
                    IssueListState.Screen(
                        talanApi.getAndroidWeeklyIssues().first().entries,
                        IssueListState.Mode.IDLE
                    )
                )
            }
            .catch<IssueListState> { cause ->
                emit(IssueListState.Error(cause))
            }
            .flowOn(dispatcherProvider.io())
    }
}
