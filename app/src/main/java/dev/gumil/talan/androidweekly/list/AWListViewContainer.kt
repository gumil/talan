package dev.gumil.talan.androidweekly.list

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.stateFor
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.gumil.talan.androidweekly.EntryTypeUi
import dev.gumil.talan.androidweekly.IssueEntryUi
import dev.gumil.talan.androidweekly.mapToModel
import dev.gumil.talan.ui.ErrorSnackbar
import dev.gumil.talan.ui.SwipeToRefreshLayout

internal class AWListViewContainer: AWListContainer {

    private val state: MutableState<IssueListStateUi> = mutableStateOf(IssueListStateUi.Screen(
        loadingMode = IssueListStateUi.Mode.LOADING)
    )

    override var actions: (IssueListAction) -> Unit = {}
    override val currentState: IssueListStateUi
        get() = state.value

    override fun setState(issueListStateUi: IssueListStateUi) {
        state.value = issueListStateUi
    }

    @Composable
    override fun render() {
        return when (val state = state.value) {
            is IssueListStateUi.Screen -> screen(state)
            is IssueListStateUi.GoToDetail -> TODO()
        }
    }

    @Composable
    private fun screen(state: IssueListStateUi.Screen) {
        val (showSnackbarError, updateShowSnackbarError) = remember(state) {
            mutableStateOf(({ state.exception != null })())
        }

        Stack {
            Column {
                toolbar()
                content(state)
            }
            ErrorSnackbar(
                showError = showSnackbarError,
                modifier = Modifier.gravity(Alignment.BottomCenter),
                onDismiss = { updateShowSnackbarError(false) }
            )
        }
    }

    @Composable
    private fun toolbar() {
        TopAppBar(title = {
            Text(text = "Android Weekly")
        })
    }

    @Composable
    private fun content(state: IssueListStateUi.Screen) {
        SwipeToRefreshLayout(
            refreshingState = state.loadingMode == IssueListStateUi.Mode.LOADING,
            onRefresh = { actions(IssueListAction.Refresh) },
            refreshIndicator = {
                Surface(elevation = 10.dp, shape = CircleShape) {
                    CircularProgressIndicator(Modifier.preferredSize(50.dp).padding(4.dp))
                }
            }
        ) {
            LazyColumnFor(state.issues) { item ->
                listItem(item = item)
            }
        }
    }

    @Composable
    private fun listItem(item: IssueEntryUi) {
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth().then(Modifier.padding(8.dp))
                .clickable(onClick = {
                    actions(IssueListAction.OnItemClick(item.mapToModel()))
                })
        ) {
            ListItem(
                text = {
                    Text(
                        text = item.title,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                },
                secondaryText = {
                    Text(
                        text = item.description,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                },
                overlineText = {
                    Text(text = item.link)
                }
            )
        }
    }

    @Preview
    @Composable
    fun listPreview() {
        val items = (0..20).map {
            IssueEntryUi(
                "title $it",
                "description $it",
                "image $it",
                "link $it",
                "host $it",
                false,
                EntryTypeUi.ARTICLE
            )
        }
        screen(IssueListStateUi.Screen(items, IssueListStateUi.Mode.IDLE))
    }
}
