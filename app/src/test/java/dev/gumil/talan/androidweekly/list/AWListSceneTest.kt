package dev.gumil.talan.androidweekly.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.gumil.talan.FakeViewModel
import dev.gumil.talan.TestDispatcherProvider
import dev.gumil.talan.TestDispatcherRule
import dev.gumil.talan.network.EntryType
import dev.gumil.talan.network.IssueEntry
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

class AWListSceneTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val initialState = IssueListState.Screen()

    private val viewModel = FakeViewModel<IssueListAction, IssueListState>(
        initialState
    )

    private val dispatcherProvider = TestDispatcherProvider()

    private val scene = AWListScene(viewModel, dispatcherProvider)

    private val container = Container(initialState)

    @Test
    fun `starting scene`() {
        // When
        scene.onStart()

        // Then
        viewModel.verifyDispatchedAction(IssueListAction.Refresh)
    }

    @Test
    fun `restoring scene`() {
        // Given
        scene.attach(container)
        viewModel.givenNextState(IssueListState.Screen())

        // When
        val saveInstanceState = scene.saveInstanceState()
        val newScene = AWListScene(viewModel, dispatcherProvider, AWListScene.getInitialState(saveInstanceState))
        newScene.onStart()
        newScene.attach(container)

        // Then
        viewModel.verifyDispatchedAction(null)
    }

    @Test
    fun `scene is reattached`() {
        // Given
        scene.attach(container)
        val state = IssueListState.GoToDetail(
            IssueEntry(
                Random.nextInt().toString(),
                Random.nextInt().toString(),
                Random.nextInt().toString(),
                Random.nextInt().toString(),
                Random.nextInt().toString(),
                Random.nextBoolean(),
                EntryType.ARTICLE
            )
        )
        viewModel.givenNextState(state)
        val newContainer = Container(initialState)

        // When
        scene.attach(newContainer)

        // Then
        assertEquals(state.mapToUiModel(), newContainer.currentState)
    }

    @Test
    fun `state screen changes`() {
        // Given
        scene.onStart()
        scene.attach(container)
        val state = IssueListState.Screen(emptyList(), IssueListState.Mode.LOADING)

        // When
        viewModel.givenNextState(state)

        // Then
        assertEquals(state.mapToUiModel(), container.currentState)
    }

    @Test
    fun `state error changes`() {
        // Given
        scene.onStart()
        scene.attach(container)
        val exception = RuntimeException()
        val state = IssueListState.Screen(exception = exception)

        // When
        viewModel.givenNextState(state)

        // Then
        assertEquals(IssueListStateUi.Screen(exception = exception), container.currentState)
    }

    @Test
    fun `state go to detail changes`() {
        // Given
        scene.onStart()
        scene.attach(container)
        val state = IssueListState.GoToDetail(
            IssueEntry(
                Random.nextInt().toString(),
                Random.nextInt().toString(),
                Random.nextInt().toString(),
                Random.nextInt().toString(),
                Random.nextInt().toString(),
                Random.nextBoolean(),
                EntryType.ARTICLE
            )
        )

        // When
        viewModel.givenNextState(state)

        // Then
        assertEquals(state.mapToUiModel(), container.currentState)
    }

    @Test
    fun `action changes calls dispatch`() {
        // Given
        scene.onStart()
        scene.attach(container)
        val issue = IssueEntry(
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextInt().toString(),
            Random.nextBoolean(),
            EntryType.ARTICLE
        )
        val action = IssueListAction.OnItemClick(issue)

        // When
        container.actions(action)

        // Then
        viewModel.verifyDispatchedAction(action)
    }

    @Test
    fun `onDestroy clears viewModel and cancels job`() {
        // Given
        scene.onStart()
        scene.attach(container)

        // When
        scene.onDestroy()
        viewModel.givenNextState(IssueListState.Screen(exception = RuntimeException()))

        // Then
        viewModel.verifyCleared()

        assertEquals(IssueListStateUi.Screen(), container.currentState)
    }

    class Container(initialState: IssueListState): AWListContainer {
        private val state: MutableState<IssueListStateUi> = mutableStateOf(initialState.mapToUiModel())
        override var actions: (IssueListAction) -> Unit = {}
        override val currentState: IssueListStateUi
            get() = state.value

        override fun setState(issueListStateUi: IssueListStateUi) {
            state.value = issueListStateUi
        }

        override fun render() {}
    }
}
