package com.example.architecturebase

class MvpPresenter(private val mvpView: MvpContract.IView) : MvpContract.IPresenter {

    private val model: MvpContract.IModel = MvpModel()

    override fun getData() {
        model.loadData({
            mvpView.setData(it)
        }, {
            mvpView.showFailure(it)
        })
    }
}