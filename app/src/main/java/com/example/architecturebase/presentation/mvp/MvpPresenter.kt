package com.example.architecturebase.presentation.mvp

import com.example.architecturebase.data.Repository
import com.example.architecturebase.domain.UsesCasesGetPosts

class MvpPresenter(private val view: MvpContract.IView) : MvpContract.IPresenter{

    // should be injected with DI to become nice
    private val usesCasesGetPosts = UsesCasesGetPosts(Repository())

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