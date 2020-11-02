package dev.gumil.talan.util

import com.arkivanov.decompose.instancekeeper.InstanceKeeper
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface State

@ExperimentalCoroutinesApi
interface ViewModel<S: State>: InstanceKeeper.Instance {
    val state: Value<S>
}
