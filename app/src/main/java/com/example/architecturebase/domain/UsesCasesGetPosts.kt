package com.example.architecturebase.domain

import com.example.architecturebase.data.Repository
import com.example.architecturebase.network.model.Post

class UsesCasesGetPosts() {

    private val repository = Repository()

    fun getPosts(callback: (List<Post>) -> Unit){
        repository.getPosts { posts ->
            posts.filter {
                !it.title.startsWith("H")
            }.map {
                it.copy(title = it.title + "appendix")
            }.sortedBy {
                it.title
            }.subList(0, posts.size - 3)
            callback.invoke(posts) }
    }
}