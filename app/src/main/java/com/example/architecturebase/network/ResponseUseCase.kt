package com.example.architecturebase.network

import com.example.architecturebase.network.model.Post
import retrofit2.Call
import retrofit2.Response

class ResponseUseCase : ResponseContract {

    override fun onResponse(
        call: Call<List<Post>>,
        response: Response<List<Post>>
    ): List<Post> {
        var processedPosts = emptyList<Post>()
        response.body()?.let { posts ->
            // logic starts
            processedPosts = posts.filter {
                !it.title.startsWith("H")
            }.map {
                it.copy(title = it.title + "appendix")
            }.sortedBy {
                it.title
            }.subList(0, posts.size - 3)
            // logic ends
        }
        return processedPosts
    }
}