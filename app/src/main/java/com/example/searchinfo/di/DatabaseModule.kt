package com.example.searchinfo.di

import android.content.Context
import com.example.searchinfo.room.DatabaseProvider
import com.example.searchinfo.room.SearchDao
import com.example.searchinfo.room.SearchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun databaseProvider(
        @ApplicationContext context: Context
    ) : SearchDatabase = DatabaseProvider.provideDB(context)

    @Provides
    @Singleton
    fun searchDatabase(
        searchDatabase: SearchDatabase
    ) : SearchDao {
        return searchDatabase.searchDao()
    }
}