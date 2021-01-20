package com.example.architecturebase.domain.usecases

import com.example.architecturebase.domain.models.Post
import com.example.architecturebase.domain.irepositories.IRemoteRepository
import retrofit2.Call

class GetPostsUseCase(private val repository: IRemoteRepository) : BaseUseCase {
    override fun execute(): Call<List<Post>> = repository.getPosts()
}