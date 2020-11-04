package dev.gumil.talan.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.lifecycle.LifecycleRegistry

class ComponentHolder<T>(
    factory: (ComponentContext) -> T
) {
    val lifecycle: LifecycleRegistry = LifecycleRegistry()
    val component: T = factory(DefaultComponentContext(lifecycle))

    fun init() {
        lifecycle.onCreate()
    }

    fun deInit() {
        lifecycle.onPause()
    }
}
