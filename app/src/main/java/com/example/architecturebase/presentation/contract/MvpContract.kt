package com.example.architecturebase.presentation.contract

import com.example.architecturebase.domain.entities.Post

// контракт
interface MvpContract {
    // View и его методы
    interface IView {
        fun setItems(items: List<Post>)
        fun setRefreshing(flag: Boolean)
        fun showFailToast(msg: String)
    }

    // Presenter и его метод
    interface IPresenter {
        fun getItemsFromNetwork()
    }
}