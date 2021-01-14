package com.example.architecturebase.mvvm.notifier

class NotifierUpdater<T>: INotifier<T>, IUpdater<T> {

    private var data: T? = null
    private var listener: (T) -> Unit = {}

    override fun notifyChanges() {
        data?.let {
            listener(it)
        }
    }

    override fun setValue(newValue: T) {
        data = newValue
        notifyChanges()
    }

    override fun listenValue(listener: (T) -> Unit) {
        this.listener = listener
    }

}