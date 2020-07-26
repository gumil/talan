package dev.gumil.talan.androidweekly.list

import com.nhaarman.acorn.presentation.SavableScene
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.SceneState
import com.nhaarman.acorn.state.sceneState
import dev.gumil.talan.network.TalanApi
import dev.gumil.talan.util.DispatcherProvider
import dev.gumil.talan.util.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class AWListScene(
    private val viewModel: ViewModel<IssueListAction, IssueListState>,
    dispatcherProvider: DispatcherProvider,
    private val initialState: IssueListState? = null
): Scene<AWListContainer>, SavableScene {

    private val scopeJob = Job()
    private var stateJob: Job? = null
    private val sceneScope = CoroutineScope(dispatcherProvider.main() + scopeJob)

    override fun onStart() {
        initialState ?: run {
            viewModel.dispatch(IssueListAction.Refresh)
        }
    }

    override fun attach(v: AWListContainer) {
        stateJob = viewModel.state
            .onEach { v.setState(it.mapToUiModel()) }
            .launchIn(sceneScope)

        v.actions = { viewModel.dispatch(it) }
    }

    override fun onStop() {
        stateJob?.cancel()
    }

    override fun saveInstanceState(): SceneState {
        return sceneState { state ->
            state.setUnchecked(KEY_STATE, viewModel.state.value.mapToUiModel())
        }
    }

    override fun onDestroy() {
        viewModel.clear()
        scopeJob.cancel()
    }

    companion object {
        private const val KEY_STATE = "key_state"

        fun newInstance(
            talanApi: TalanApi,
            dispatcherProvider: DispatcherProvider,
            sceneState: SceneState? = null
        ): AWListScene {
            val initialState = getInitialState(sceneState)
            val viewModel = getViewModel(initialState, talanApi, dispatcherProvider)

            return AWListScene(
                viewModel,
                dispatcherProvider,
                initialState
            )
        }

        private fun getViewModel(
            initialState: IssueListState?,
            talanApi: TalanApi,
            dispatcherProvider: DispatcherProvider
        ): ViewModel<IssueListAction, IssueListState> {
            val issueListState = initialState ?: IssueListState.Screen(loadingMode = IssueListState.Mode.LOADING)
            return getAndroidWeekly(issueListState, talanApi, dispatcherProvider)
        }

        internal fun getInitialState(sceneState: SceneState?): IssueListState? {
            return (sceneState?.getUnchecked(KEY_STATE) as? IssueListStateUi)?.mapToModel()
        }

        private fun getAndroidWeekly(
            initialState: IssueListState,
            talanApi: TalanApi,
            dispatcherProvider: DispatcherProvider
        ): AndroidWeeklyViewModel {
            return AndroidWeeklyViewModel(
                talanApi,
                dispatcherProvider,
                initialState
            )
        }
    }
}
