package com.example.architecturebase.presentation.mvp

import com.example.architecturebase.domain.Post

interface MvpContract {

    interface IView{
        fun showNewPosts(posts: List<Post>)
        fun showError()
    }

    interface IPresenter{
        fun loadNewPosts()
    }

}
