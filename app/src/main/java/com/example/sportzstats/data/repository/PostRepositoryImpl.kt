package com.example.sportzstats.data.repository

import com.example.sportzstats.domain.model.Post
import com.example.sportzstats.domain.repository.PostRepository
import javax.inject.Inject
import com.example.sportzstats.data.remote.PostApiService
import com.google.gson.Gson
import com.google.gson.JsonObject

class PostRepositoryImpl @Inject constructor(
    private val apiService: PostApiService
) : PostRepository {

    override suspend fun fetchPosts(): Post? {
        try {
            val response = apiService.getMatchStats()
            if (response.isSuccessful) {
                val jsonResponse: JsonObject? = response.body()
                val gson = Gson()
                val post = gson.fromJson(jsonResponse, Post::class.java)
                return post
            } else {
                throw Exception("Error fetching posts. Code: ${response.code()}")
            }
        } catch (e: Exception) {
            throw Exception("Network error: ${e.message}")
        }
    }
}