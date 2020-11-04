package dev.gumil.talan.util

import com.arkivanov.decompose.instancekeeper.InstanceKeeper
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface StateUi

@ExperimentalCoroutinesApi
interface ViewModel<S: StateUi>: InstanceKeeper.Instance {
    val state: Value<S>
}
