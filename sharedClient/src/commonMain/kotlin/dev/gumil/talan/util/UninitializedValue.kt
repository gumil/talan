package dev.gumil.talan.util

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.ValueObserver

class UninitializedValue<T: Any>: Value<T>() {
    private var observers = emptySet<ValueObserver<T>>()

    override val value: T
        get() = _value ?: throw IllegalStateException("Value not yet initialized")

    private var _value: T? = null
        set(value) {
            value ?: return
            observers.forEach { it(value) }
        }

    fun setValue(value: T) {
        _value = value
    }

    override fun subscribe(observer: ValueObserver<T>) {
        observers = observers + observer
        _value?.let { observer(it) }
    }

    override fun unsubscribe(observer: ValueObserver<T>) {
        observers = observers - observer
    }
}
