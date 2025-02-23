package com.example.sportzstats.domain.repository

import com.example.sportzstats.domain.model.Post

interface PostRepository {
    suspend fun fetchPosts(): Post?
}
