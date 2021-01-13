package com.example.architecturebase.mvp

import com.example.architecturebase.network.model.Post

interface MvpContract {

    interface IView{
        fun showNewPosts(posts: List<Post>)
        fun showError()
    }

    interface IPresenter{
        fun loadNewPosts()
    }

}
