package com.example.architecturebase

import com.example.architecturebase.network.IPostApi

class PostPresenter : PostContract.IPostPresenter {

    override fun getPostsApi(): IPostApi = RetrofitInstance.getRetrofit()

}