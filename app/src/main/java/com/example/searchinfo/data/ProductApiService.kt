package com.example.searchinfo.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductApiService {
    @GET("v2/search/image")
    suspend fun getProducts(
        @Header("Authorization") apikey: String = Url.AUTH_KEY,
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ImageResponse>
}