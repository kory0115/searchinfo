package com.example.searchinfo.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.searchinfo.data.ImageEntity
import com.example.searchinfo.data.ImageResponse
import com.example.searchinfo.data.ProductApiService
import com.example.searchinfo.page.PagingSourc
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DefaultRetrofitRepository @Inject constructor(
    private val productApiService: ProductApiService
) : RetrofitRepository{
    override suspend fun productApi(query: String, sort: String): Response<ImageResponse> {
        return productApiService.getProducts(query = query, sort = sort, page = 1, size = 80)
    }

    override fun searchImagePaging(query: String, sort: String): Flow<PagingData<ImageEntity>> {
        val pagingSourceFactory = { PagingSourc(productApiService, query, sort) }

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                maxSize = 10 * 3
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}