package com.example.sportzstats.data.remote

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET

interface PostApiService {
    @GET("nzin01312019187360.json")
    suspend fun getMatchDetailsOne():  Response<JsonObject>

    @GET("sapk01222019186652.json")
    suspend fun getMatchDetailsTwo():  Response<JsonObject>
}