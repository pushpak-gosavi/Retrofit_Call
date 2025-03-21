package com.pushpak.retrofitcall.api

import com.pushpak.retrofitcall.model.Articles
import retrofit2.http.GET
import retrofit2.http.Query

interface APICall {
    @GET("top-headlines")
    suspend fun getTopHedLines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Articles
}