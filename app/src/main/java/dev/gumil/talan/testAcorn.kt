package dev.gumil.talan

import android.util.Log
import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.material.Button
import androidx.ui.material.Scaffold
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import dev.gumil.talan.acorn.ComposeContainer
import dev.gumil.talan.acorn.ComposeContainerFactory

internal class Home: ComposeContainer {
    var onClick: () -> Unit = {
        Log.d("tantrums", "click should not be seen")
    }

    @Composable
    override fun render() {
        Scaffold(
            bodyContent = {
                Text(text = "Hello Scene ONE!")
                Button(onClick = {
                    onClick()
                }) {
                    Text(text = "Click to go to next scene!")
                }
            }
        )
    }
}

internal class OtherScreen: ComposeContainer {
    @Composable
    override fun render() {
        Text(text = "Hello Scene OtherScreen!")
    }
}

internal class ComposeContainerFactoryImpl: ComposeContainerFactory {
    override fun composeContainerFor(scene: Scene<out Container>): ComposeContainer {
        return when(scene.key.value) {
            TestSceneOne::class.java.name -> Home()
            TestSceneTwo::class.java.name -> OtherScreen()
            else -> error("Not supported")
        }
    }
}

internal class TestSceneOne(
    private val listener: Events
): Scene<Home> {

    override fun attach(v: Home) {
        v.onClick = listener::onClickButton
    }

    interface Events {
        fun onClickButton()
    }
}

internal class TestSceneTwo: Scene<ComposeContainer>
