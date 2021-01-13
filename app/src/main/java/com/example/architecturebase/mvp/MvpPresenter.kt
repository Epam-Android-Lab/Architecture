package com.example.architecturebase.mvp

import com.example.architecturebase.domain.UsesCasesGetPosts

class MvpPresenter(private val view: MvpContract.IView) : MvpContract.IPresenter{

    private val usesCasesGetPosts = UsesCasesGetPosts()

    override fun loadNewPosts() {
        view.showNewPosts(emptyList())

        usesCasesGetPosts.getPosts { posts ->

            if (posts.isEmpty()) {
                view.showError()
                return@getPosts
            }

            // отобразить данные
            view.showNewPosts(posts)

        }
    }

}