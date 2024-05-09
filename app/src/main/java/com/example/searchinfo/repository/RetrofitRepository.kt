package com.example.searchinfo.repository

import com.example.searchinfo.data.ImageResponse
import retrofit2.Response

interface RetrofitRepository {
    suspend fun productApi(query: String, sort: String): Response<ImageResponse>
}