package com.example.searchinfo.di

import com.example.searchinfo.data.ProductApiService
import com.example.searchinfo.repository.DefaultRetrofitRepository
import com.example.searchinfo.repository.RetrofitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    fun provideSearchRepository(
        productApiService: ProductApiService
    ) : RetrofitRepository = DefaultRetrofitRepository(productApiService)
}