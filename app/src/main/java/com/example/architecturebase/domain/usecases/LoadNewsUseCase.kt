package com.example.architecturebase.domain.usecases

import com.example.architecturebase.data.repos.NewsRepoImpl
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.domain.repos.NewsRepo
import retrofit2.Response

class LoadNewsUseCase {

    private val repo: NewsRepo = NewsRepoImpl()

    suspend fun loadNews(): Response<List<Post>> {
        return repo.getNews()
    }
    suspend fun pushPost() {
        repo.pushPost(Post(1337, "Gamlet", "Halkovich"))
    }

}