package com.example.architecturebase.presentation

import com.example.architecturebase.domain.models.Post

interface MvpContract {
    interface IView {
        fun setData(data: List<Post>)
        fun showFailure(failure: Throwable)
    }

    interface IPresenter {
        fun loadPosts()
    }
}