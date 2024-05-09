package com.example.searchinfo.di

import com.example.searchinfo.data.ProductApiService
import com.example.searchinfo.repository.DefaultRetrofitRepository
import com.example.searchinfo.repository.DefaultRoomRepository
import com.example.searchinfo.repository.RetrofitRepository
import com.example.searchinfo.repository.RoomRepository
import com.example.searchinfo.room.SearchDao
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    fun dbSearchRepository(
       searchDao: SearchDao
    ) : RoomRepository = DefaultRoomRepository(searchDao)
}