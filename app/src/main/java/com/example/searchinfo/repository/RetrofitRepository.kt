package com.example.searchinfo.repository

import androidx.paging.PagingData
import com.example.searchinfo.data.ImageEntity
import com.example.searchinfo.data.ImageResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RetrofitRepository {
    suspend fun productApi(query: String, sort: String): Response<ImageResponse>

    fun searchImagePaging(query: String, sort: String): Flow<PagingData<ImageEntity>>
}