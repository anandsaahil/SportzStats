package com.example.sportzstats.data.repository

import com.example.sportzstats.data.exceptionHandlers.HttpException
import com.example.sportzstats.data.exceptionHandlers.NetworkException
import com.example.sportzstats.data.exceptionHandlers.UnknownException
import com.example.sportzstats.domain.model.Post
import com.example.sportzstats.domain.repository.PostRepository
import javax.inject.Inject
import com.example.sportzstats.data.remote.PostApiService
import com.example.sportzstats.data.typeAdapter.TeamsTypeAdapter
import com.example.sportzstats.domain.model.Teams
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.io.IOException

class PostRepositoryImpl @Inject constructor(
    private val apiService: PostApiService
) : PostRepository {

    override suspend fun getMatchDetailsOne(): Post? {
        return try {
            val response = apiService.getMatchDetailsOne()
            val gson = GsonBuilder()
                .registerTypeAdapter(Teams::class.java, TeamsTypeAdapter())
                .create()

            if (response.isSuccessful) {
                val jsonResponse: JsonObject? = response.body()
                gson.fromJson(jsonResponse, Post::class.java)
            } else {
                throw HttpException("HTTP error: ${response.code()} - ${response.message()}")
            }
        } catch (e: IOException) {
            throw NetworkException("Network error: ${e.message}", e)
        } catch (e: HttpException) {
            throw HttpException("HTTP error: ${e.message}", e)
        } catch (e: Exception) {
            throw UnknownException("Unknown error: ${e.message}", e)
        }
    }

    override suspend fun getMatchDetailsTwo(): Post? {
        return try {
            val response = apiService.getMatchDetailsTwo()
            val gson = GsonBuilder()
                .registerTypeAdapter(Teams::class.java, TeamsTypeAdapter())
                .create()

            if (response.isSuccessful) {
                val jsonResponse: JsonObject? = response.body()
                gson.fromJson(jsonResponse, Post::class.java)
            } else {
                throw HttpException("HTTP error: ${response.code()} - ${response.message()}")
            }
        } catch (e: IOException) {
            throw NetworkException("Network error: ${e.message}", e)
        } catch (e: HttpException) {
            throw HttpException("HTTP error: ${e.message}", e)
        } catch (e: Exception) {
            throw UnknownException("Unknown error: ${e.message}", e)
        }
    }
}
