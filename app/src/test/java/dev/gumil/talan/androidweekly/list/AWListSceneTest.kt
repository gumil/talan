package dev.gumil.talan.androidweekly.list

import androidx.compose.FrameManager
import androidx.compose.MutableState
import androidx.compose.mutableStateOf
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
    fun `restoring scene`() = FrameManager.framed {
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
    fun `scene is reattached`() = FrameManager.framed {
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
        assertEquals(state.mapToUiModel(), newContainer.state.value)
    }

    @Test
    fun `state screen changes`() = FrameManager.framed {
        // Given
        scene.onStart()
        scene.attach(container)
        val state = IssueListState.Screen(emptyList(), IssueListState.Mode.LOADING)

        // When
        viewModel.givenNextState(state)

        // Then
        assertEquals(state.mapToUiModel(), container.state.value)
    }

    @Test
    fun `state error changes`() = FrameManager.framed {
        // Given
        scene.onStart()
        scene.attach(container)
        val exception = RuntimeException()
        val state = IssueListState.Error(exception)

        // When
        viewModel.givenNextState(state)

        // Then
        assertEquals(IssueListStateUi.Screen(exception = exception), container.state.value)
    }

    @Test
    fun `state go to detail changes`() = FrameManager.framed {
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
        assertEquals(state.mapToUiModel(), container.state.value)
    }

    @Test
    fun `action changes calls dispatch`() = FrameManager.framed {
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
    fun `onDestroy clears viewModel and cancels job`() = FrameManager.framed {
        // Given
        scene.onStart()
        scene.attach(container)

        // When
        scene.onDestroy()
        viewModel.givenNextState(IssueListState.Error(RuntimeException()))

        // Then
        viewModel.verifyCleared()

        assertEquals(IssueListStateUi.Screen(), container.state.value)
    }

    class Container(initialState: IssueListState): AWListContainer {
        override val state: MutableState<IssueListStateUi> = mutableStateOf(initialState.mapToUiModel())
        override var actions: (IssueListAction) -> Unit = {}
        override fun render() {}
    }
}
