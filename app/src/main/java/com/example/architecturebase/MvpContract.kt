package com.example.architecturebase

import com.example.architecturebase.network.model.Post

// контракт
interface MvpContract {
    // View и его методы
    interface IView {
        fun setItems(items: List<Post>)
        fun setRefreshing(flag: Boolean)
        fun showToast(t: Throwable)
    }

    // Presenter и его метод
    interface IPresenter {
        fun getItemsFromNetwork()
    }
}