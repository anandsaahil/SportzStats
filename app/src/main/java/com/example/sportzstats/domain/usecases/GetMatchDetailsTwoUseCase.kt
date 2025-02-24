package com.example.sportzstats.domain.usecases


import com.example.sportzstats.domain.model.Post
import com.example.sportzstats.domain.repository.PostRepository
import javax.inject.Inject

class GetMatchDetailsTwoUseCase @Inject constructor(private val postRepository: PostRepository) {
    suspend operator fun invoke(): Post? {
        return postRepository.getMatchDetailsTwo()
    }
}
