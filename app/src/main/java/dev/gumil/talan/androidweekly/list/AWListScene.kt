package dev.gumil.talan.androidweekly.list

import com.nhaarman.acorn.presentation.SavableScene
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.SceneState
import com.nhaarman.acorn.state.sceneState
import dev.gumil.talan.network.NetworkModule
import dev.gumil.talan.util.DispatcherProvider
import dev.gumil.talan.util.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.scanReduce

internal class AWListScene(
    private val viewModel: ViewModel<IssueListAction, IssueListState>,
    dispatcherProvider: DispatcherProvider,
    private val initialState: IssueListState? = null
): Scene<AWListContainer>, SavableScene {

    private val job = Job()

    private val sceneScope = CoroutineScope(dispatcherProvider.main() + job)

    override fun onStart() {
        initialState ?: run {
            viewModel.dispatch(IssueListAction.Refresh)
        }
    }

    override fun attach(v: AWListContainer) {
        viewModel.state
            .scanReduce { accumulator, value ->
                v.state.value = value.mapToUiModel(accumulator)
                if (value is IssueListState.Screen) value
                else accumulator
            }
            .launchIn(sceneScope)

        v.actions = { viewModel.dispatch(it) }
    }

    override fun saveInstanceState(): SceneState {
        return sceneState { state ->
            state.setUnchecked(KEY_STATE, viewModel.state.value.mapToUiModel())
        }
    }

    override fun onDestroy() {
        viewModel.clear()
        job.cancel()
    }

    companion object {
        private const val KEY_STATE = "key_state"

        fun newInstance(
            sceneState: SceneState?,
            dispatcherProvider: DispatcherProvider
        ): AWListScene {
            val initialState = getInitialState(sceneState)
            val viewModel = getViewModel(initialState, dispatcherProvider)

            return AWListScene(
                viewModel,
                dispatcherProvider,
                initialState
            )
        }

        private fun getViewModel(
            initialState: IssueListState?,
            dispatcherProvider: DispatcherProvider
        ): ViewModel<IssueListAction, IssueListState> {
            val issueListState = initialState ?: IssueListState.Screen(loadingMode = IssueListState.Mode.LOADING)
            return getAndroidWeekly(issueListState, dispatcherProvider)
        }

        internal fun getInitialState(sceneState: SceneState?): IssueListState? {
            return (sceneState?.getUnchecked(KEY_STATE) as? IssueListStateUi)?.mapToModel()
        }

        private fun getAndroidWeekly(
            initialState: IssueListState,
            dispatcherProvider: DispatcherProvider
        ): AndroidWeeklyViewModel {
            return AndroidWeeklyViewModel(
                NetworkModule.provideTalanApi(),
                dispatcherProvider,
                initialState
            )
        }
    }
}
