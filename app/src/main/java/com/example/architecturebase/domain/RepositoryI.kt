package com.example.architecturebase.domain

interface RepositoryI {
    fun getPosts(callback: (List<Post>) -> Unit, errorCallback: (Throwable) -> Unit)
}