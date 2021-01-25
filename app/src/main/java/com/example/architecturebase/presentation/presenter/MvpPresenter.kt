package com.example.architecturebase.presentation.presenter

import com.example.architecturebase.data.repos.NewsRepoImpl
import com.example.architecturebase.domain.usecases.LoadNewsUseCase
import com.example.architecturebase.presentation.contract.MvpContract

class MvpPresenter(private val mvpView: MvpContract.IView) : MvpContract.IPresenter {

    private val newsUseCase: LoadNewsUseCase = LoadNewsUseCase()


    override fun getItemsFromNetwork(){
        newsUseCase.loadNews(
            mvpView::setItems,
            mvpView::setRefreshing,
            mvpView::showFailToast)
    }

}