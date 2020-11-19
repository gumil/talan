package dev.gumil.talan

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.arkivanov.decompose.extensions.compose.jetpack.rootComponent
import dev.gumil.talan.androidweekly.render
import dev.gumil.talan.androidweekly.root.AWRoot

internal class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    rootComponent { componentContext ->
                        AWRoot(componentContext)
                    }.model.render {
                        CustomTabsIntent.Builder().build().launchUrl(this, Uri.parse(it.link))
                    }
                }
            }
        }
    }
}
