package com.example.architecturebase.presentation.contract

import com.example.architecturebase.data.api.IPostApi

interface PostContract {
    interface IPostPresenter {
        fun getNews()
    }
}