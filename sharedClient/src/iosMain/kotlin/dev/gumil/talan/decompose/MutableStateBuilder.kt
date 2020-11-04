package dev.gumil.talan.decompose

import com.arkivanov.decompose.value.MutableValue

fun <T: Any> mutableValue(initialValue: T): MutableValue<T> {
    return MutableValue(initialValue)
}
