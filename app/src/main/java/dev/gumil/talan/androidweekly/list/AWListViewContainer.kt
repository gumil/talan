package dev.gumil.talan.androidweekly.list

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.mutableStateOf
import androidx.compose.stateFor
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Stack
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredSize
import androidx.ui.material.Card
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.ListItem
import androidx.ui.material.Surface
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
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
        val (showSnackbarError, updateShowSnackbarError) = stateFor(state) { state.exception != null }

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
            LazyColumnItems(state.issues) { item ->
                listItem(item = item)
            }
        }
    }

    @Composable
    private fun listItem(item: IssueEntryUi) {
        Card(
            shape = RoundedCornerShape(4.dp), color = Color.White,
            modifier = (Modifier.fillMaxWidth() + Modifier.padding(8.dp))
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
