package com.example.architecturebase

import com.example.architecturebase.network.model.Post

interface MvpContract {
    interface IView {
        fun setData(data: List<Post>)
        fun showFailure(failure: Throwable)
    }

    interface IPresenter {
        fun getData()
    }

    interface IModel {
        fun loadData(
            dataLoadedCallback: (List<Post>) -> Unit,
            failureCallback: (t: Throwable) -> Unit
        )
    }
}