package com.example.architecturebase.mvvm.notifier

interface IUpdater<T> {
    fun setValue(newValue: T)
}