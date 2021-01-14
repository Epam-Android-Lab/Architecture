package com.example.architecturebase.mvvm.notifier

interface INotifier<T> {
    fun notifyChanges()
    fun listenValue(listener: (T) -> Unit)
}