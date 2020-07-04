package dev.gumil.talan.androidweekly.list

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Card
import androidx.ui.material.ListItem
import androidx.ui.material.TopAppBar
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import dev.gumil.talan.acorn.ComposeContainer
import dev.gumil.talan.network.EntryType
import dev.gumil.talan.network.IssueEntry

internal class AndroidWeeklyListContainer: ComposeContainer {

    val items = (0..20).map {
        IssueEntry(
            "title $it",
            "description $it",
            "image $it",
            "link $it",
            "host $it",
            false,
            EntryType.ARTICLE
        )
    }

    @Preview
    @Composable
    override fun render() {
        Column {
            toolbar()
            content()
        }
    }

    @Composable
    private fun toolbar() {
        TopAppBar(title = {
            Text(text = "Android Weekly")
        })
    }

    @Composable
    private fun content() {
        LazyColumnItems(items) { item ->
            listItem(item = item)
        }
    }

    @Composable
    private fun listItem(item: IssueEntry) {
        Card(
            shape = RoundedCornerShape(4.dp), color = Color.White,
            modifier = (Modifier.fillMaxWidth() + Modifier.padding(8.dp))
                    .clickable(onClick = {})
        ) {
            ListItem(
                text = {
                    Text(
                        text = item.title
                    )
                },
                secondaryText = {
                    Text(text = item.description)
                },
                overlineText = {
                    Text(text = item.link)
                }
            )
        }
    }
}
