package dev.gumil.talan.androidweekly

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.observe
import dev.gumil.talan.androidweekly.list.AWList
import dev.gumil.talan.network.EntryType
import dev.gumil.talan.ui.SwipeToRefreshLayout

@Composable
operator fun AWList.Model.invoke() {
    state.observe {
        screen(it, this)
    }
}

@Composable
private fun screen(state: AWList.Screen, events: AWList.Events) {
    Box {
        Column {
            toolbar()
            content(state, events)
        }
    }
}

@Composable
private fun toolbar() {
    TopAppBar(title = {
        Text(text = "Android Weekly")
    })
}

@Composable
private fun content(state: AWList.Screen, events: AWList.Events) {
    SwipeToRefreshLayout(
        refreshingState = state.loadingMode == AWList.Mode.LOADING,
        onRefresh = { events.refresh() },
        refreshIndicator = {
            Surface(elevation = 10.dp, shape = CircleShape) {
                CircularProgressIndicator(Modifier.preferredSize(50.dp).padding(4.dp))
            }
        }
    ) {
        LazyColumnFor(state.issues) { item ->
            listItem(item = item, events::onItemClicked)
        }
    }
}

@Composable
private fun listItem(item: IssueEntryUi, onItemClicked: (IssueEntryUi) -> Unit) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.fillMaxWidth().then(Modifier.padding(8.dp))
            .clickable(onClick = { onItemClicked(item) })
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
            EntryType.ARTICLE
        )
    }
    screen(AWList.Screen(items, AWList.Mode.IDLE), object : AWList.Events {
        override fun onItemClicked(issueEntry: IssueEntryUi) {}
        override fun refresh() {}
    })
}
