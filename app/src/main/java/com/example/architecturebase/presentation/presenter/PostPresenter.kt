package com.example.architecturebase.presentation.presenter

import com.example.architecturebase.data.repos.NewsRepoImpl
import com.example.architecturebase.presentation.viewmodel.PostsViewModel
import com.example.architecturebase.presentation.contract.PostContract

class PostPresenter(viewModel: PostsViewModel) : PostContract.IPostPresenter {

    private val repo = NewsRepoImpl(viewModel)

    override fun getNews() {
        repo.getNews()
    }

}