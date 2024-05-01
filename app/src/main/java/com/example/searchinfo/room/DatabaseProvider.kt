package com.example.searchinfo.room

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    const val DB_NAME = "search_repository_app.db"

    fun provideDB(applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        SearchDatabase::class.java,
        DB_NAME
    ).build()
}