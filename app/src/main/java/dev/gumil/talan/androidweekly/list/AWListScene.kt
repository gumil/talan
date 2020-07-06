package dev.gumil.talan.androidweekly.list

import androidx.compose.MutableState
import androidx.compose.mutableStateOf
import com.nhaarman.acorn.presentation.SavableScene
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.SceneState
import com.nhaarman.acorn.state.sceneState
import dev.gumil.talan.network.NetworkModule
import dev.gumil.talan.util.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class AWListScene(
    sceneState: SceneState?,
    private val dispatcherProvider: DispatcherProvider
): Scene<AWListContainer>, SavableScene {

    private val initialState = getInitialState(sceneState)

    private val viewModel: AndroidWeeklyViewModel

    private val job = Job()

    private val sceneScope = CoroutineScope(dispatcherProvider.main() + job)

    init {
        val issueListState = initialState ?: IssueListState.Screen(loadingMode = IssueListState.Mode.LOADING)
        viewModel = getAndroidWeekly(issueListState)
    }

    override fun onStart() {
        initialState ?: run {
            viewModel.dispatch(IssueListAction.Refresh)
        }
    }

    override fun attach(v: AWListContainer) {
        viewModel.state
            .onEach { v.state.value = it.mapToUiModel() }
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

    private fun getAndroidWeekly(initialState: IssueListState): AndroidWeeklyViewModel {
        return AndroidWeeklyViewModel(
            NetworkModule.provideTalanApi(),
            dispatcherProvider,
            initialState
        )
    }

    private fun getInitialState(sceneState: SceneState?): IssueListState? {
        return (sceneState?.getUnchecked(KEY_STATE) as? IssueListStateUi)?.mapToModel()
    }

    companion object {
        private const val KEY_STATE = "key_state"
    }
}
