package dev.gumil.talan

import android.util.Log
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Card
import androidx.ui.material.ListItem
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import dev.gumil.talan.acorn.ComposeContainer
import dev.gumil.talan.acorn.ComposeContainerFactory

internal class Home : ComposeContainer {
    var onClick: () -> Unit = {
        Log.d("tantrums", "click should not be seen")
    }

    val items = (0..20).map {
        "Hello $it" to ("world ${20 - it}")
    }

    @Preview
    @Composable
    override fun render() {
        LazyColumnItems(items) { hello ->
            Card(
                shape = RoundedCornerShape(4.dp), color = Color.White,
                modifier = Modifier.fillMaxWidth() + Modifier.padding(8.dp)
            ) {
                ListItem(
                    text = {
                        Text(text = hello.first)
                    },
                    secondaryText = {
                        Text(text = hello.second)
                    }
                )
            }
        }
    }
}

internal class OtherScreen : ComposeContainer {
    @Composable
    override fun render() {
        Text(text = "Hello Scene OtherScreen!")
    }
}

internal class ComposeContainerFactoryImpl : ComposeContainerFactory {
    override fun composeContainerFor(scene: Scene<out Container>): ComposeContainer {
        return when (scene.key.value) {
            TestSceneOne::class.java.name -> Home()
            TestSceneTwo::class.java.name -> OtherScreen()
            else -> error("Not supported")
        }
    }
}

internal class TestSceneOne(
    private val listener: Events
) : Scene<Home> {

    override fun attach(v: Home) {
        v.onClick = listener::onClickButton
    }

    interface Events {
        fun onClickButton()
    }
}

internal class TestSceneTwo : Scene<ComposeContainer>
