package com.example.architecturebase

import com.example.architecturebase.network.IPostApi

interface PostContract {
    interface IPostPresenter {
        fun getPostsApi(): IPostApi
    }
}