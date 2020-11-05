package dev.gumil.talan.androidweekly.list

import co.touchlab.stately.ensureNeverFrozen
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.androidweekly.toUiModel
import dev.gumil.talan.network.TalanApi
import dev.gumil.talan.util.DispatcherProvider
import dev.gumil.talan.util.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class AndroidWeeklyViewModel(
    private val talanApi: TalanApi,
    private val dispatcherProvider: DispatcherProvider,
    initialState: AWList.Screen = AWList.Screen()
) : ViewModel<AWList.Screen> {

    private val job = Job()

    private val uiScope = CoroutineScope(dispatcherProvider.main() + job)

    override val state: Value<AWList.Screen> get() = _state

    private val _state = MutableValue(initialState)

    init {
        ensureNeverFrozen()
        refresh()
    }

    fun refresh() {
        uiScope.launch {
            getLatestAndroidWeeklyFlow().collect {
                _state.value = it
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
    }

    private fun getLatestAndroidWeeklyFlow(): Flow<AWList.Screen> {
        return flowOf(state.value)
            .flatMapConcat { currentState ->
                flow {
                    emit(
                        AWList.Screen(
                            talanApi.getAndroidWeeklyIssues()
                                .first()
                                .entries
                                .map { it.toUiModel() },
                            AWList.Mode.IDLE
                        )
                    )
                }.onStart {
                    emit(currentState.copy(loadingMode = AWList.Mode.LOADING))
                }.catch { cause ->
                    emit(currentState.copy(loadingMode = AWList.Mode.IDLE, exception = cause))
                }
            }
            .flowOn(dispatcherProvider.io())
    }
}
