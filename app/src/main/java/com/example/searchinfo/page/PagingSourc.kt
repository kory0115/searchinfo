package com.example.searchinfo.page

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.searchinfo.data.ImageEntity
import com.example.searchinfo.data.ProductApiService
import javax.inject.Inject

class PagingSourc @Inject constructor(
    private val api : ProductApiService,
    private val query: String,
    private val sort: String
) : PagingSource<Int, ImageEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ImageEntity>) : Int? {
        return state.anchorPosition?.let { achorPosition ->
            state.closestPageToPosition(achorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(achorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageEntity> {

        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX
            val response =
                api.getProducts(
                    query = query,
                    sort = sort,
                    page = pageNumber,
                    size = params.loadSize
                )
            val endOfPaginationReached = response.body()

            val data : List<ImageEntity> = response.body()?.items!!

            val prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
            val nextKey = if (endOfPaginationReached?.meta?.isend!!) {
                null
            } else {
                pageNumber + 1
            }

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}