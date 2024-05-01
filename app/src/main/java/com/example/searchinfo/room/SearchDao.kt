package com.example.searchinfo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchDao {
    @Query("SELECT * FROM search")
    suspend fun getAll(): List<RoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSearch(roomEntity: RoomEntity)

    @Query("DELETE FROM search WHERE thumbnailurl = :imageuri")
    suspend fun deleteSearch(imageuri: String)
}