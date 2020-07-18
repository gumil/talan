package dev.gumil.talan.androidweekly.list

import dev.gumil.kaskade.ActionState
import dev.gumil.kaskade.Kaskade
import dev.gumil.kaskade.coroutines.coroutines
import dev.gumil.kaskade.coroutines.stateDamFlow
import dev.gumil.talan.network.TalanApi
import dev.gumil.talan.util.DispatcherProvider
import dev.gumil.talan.util.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.yield

@FlowPreview
@ExperimentalCoroutinesApi
class AndroidWeeklyViewModel(
    private val talanApi: TalanApi,
    private val dispatcherProvider: DispatcherProvider,
    initialState: IssueListState = IssueListState.Screen()
): ViewModel<IssueListAction, IssueListState> {

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

    override val state: StateFlow<IssueListState> get() = _state

    private val _state = kaskade.stateDamFlow()

    override fun dispatch(action: IssueListAction) {
        kaskade.dispatch(action)
    }

    override fun clear() {
        kaskade.unsubscribe()
        _state.clear()
        job.cancel()
    }

    private suspend fun <A : IssueListAction> Flow<ActionState<A, IssueListState>>.getLatestAndroidWeekly(): Flow<IssueListState> {
        return this
            .flatMapConcat { actionState ->
                val state = actionState.currentState as IssueListState.Screen
                flowOf(
                    IssueListState.Screen(
                        talanApi.getAndroidWeeklyIssues().first().entries,
                        IssueListState.Mode.IDLE
                    )
                ).onStart {
                    emit(state.copy(loadingMode = IssueListState.Mode.LOADING))

                    /**
                     * This is to make sure native emits this state since it currently only runs on
                     * the main thread
                     */
                    yield()
                }.catch<IssueListState> { cause ->
                    emit(state.copy(exception = cause))
                }
            }
            .flowOn(dispatcherProvider.io())
    }
}
