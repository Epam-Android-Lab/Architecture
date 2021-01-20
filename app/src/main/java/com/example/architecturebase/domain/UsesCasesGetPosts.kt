package com.example.architecturebase.domain

class UsesCasesGetPosts(private val repository: RepositoryI) {

    fun getPosts(callback: (List<Post>) -> Unit, errorCallBack: (Throwable) -> Unit) {
        repository.getPosts ({ posts ->
            filterPosts(posts)
            callback.invoke(posts)
        }, errorCallback = { t ->
            errorCallBack.invoke(t)
        })
    }

    private fun filterPosts(posts: List<Post>){
        posts.filter {
            !it.title.startsWith("H")
        }.map {
            it.copy(title = it.title + "appendix")
        }.sortedBy {
            it.title
        }.subList(0, posts.size - 3)
    }
}