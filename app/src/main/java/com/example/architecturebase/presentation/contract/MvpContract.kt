package com.example.architecturebase.presentation.contract

import com.example.architecturebase.domain.entities.Post

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