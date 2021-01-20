package com.example.architecturebase.domain


class UsesCasesGetPosts(private val repository: RepositoryI) {

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