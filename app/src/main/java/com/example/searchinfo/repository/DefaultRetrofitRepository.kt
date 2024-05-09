package com.example.searchinfo.repository

import com.example.searchinfo.data.ImageResponse
import com.example.searchinfo.data.ProductApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DefaultRetrofitRepository @Inject constructor(
    private val productApiService: ProductApiService
) : RetrofitRepository{
    override suspend fun productApi(query: String, sort: String): Response<ImageResponse> {
        return productApiService.getProducts(query = query, sort = sort, page = 1, size = 80)
    }
}