package dev.gumil.talan.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.launchInComposition
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
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

        AnimatedVisibility(
            visible = showError,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            modifier = modifier
        ) {
            Snackbar(
                modifier = modifier.padding(16.dp),
                text = { Text("Can't update latest issue") }
            )
        }
    }
}
