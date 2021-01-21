package com.example.architecturebase.domain

import com.example.architecturebase.data.network.IPostApi

interface RepositoryI {
    fun getPosts(callback: (List<Post>) -> Unit, errorCallback: (Throwable) -> Unit)
}