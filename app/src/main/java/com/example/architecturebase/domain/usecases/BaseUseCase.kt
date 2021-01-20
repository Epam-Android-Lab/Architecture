package com.example.architecturebase.domain.usecases

import com.example.architecturebase.domain.models.Post
import retrofit2.Call

interface BaseUseCase {
    fun execute(): Call<List<Post>>
}