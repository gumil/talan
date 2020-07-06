package dev.gumil.talan.ui

import androidx.compose.Composable
import androidx.compose.launchInComposition
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.padding
import androidx.ui.material.Snackbar
import androidx.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ErrorSnackbar(
    showError: Boolean,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = { }
) {
    if (showError) {
        launchInComposition {
            delay(timeMillis = 2000L)
            onDismiss()
        }

        Snackbar(
            modifier = modifier.padding(16.dp),
            text = { Text("Can't update latest issue") }
        )
    }
}
