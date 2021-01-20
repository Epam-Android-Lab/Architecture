package com.example.architecturebase.presentation.presenter

import com.example.architecturebase.data.repos.NewsRepoImpl
import com.example.architecturebase.presentation.contract.MvpContract

class MvpPresenter(private val mvpView: MvpContract.IView) : MvpContract.IPresenter {

    private val repo: NewsRepoImpl = NewsRepoImpl(mvpView)

    // получаем список
    override fun getItemsFromNetwork(){
        repo.getNews()
    }

}