package com.example.searchinfo.repository

import com.example.searchinfo.data.ImageResponse
import com.example.searchinfo.data.RetrofitFix
import retrofit2.Response

class RetrofitRepository {
    suspend fun productApi(query: String, sort: String): Response<ImageResponse> {
        return RetrofitFix.retrofitResult.getProducts(query = query, sort = sort, page = 1, size = 80)
    }
}