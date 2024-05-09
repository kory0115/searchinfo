package com.example.searchinfo.repository

import com.example.searchinfo.room.RoomEntity

interface RoomRepository {
    suspend fun getAll() : List<RoomEntity>

    suspend fun saveSearch(roomEntity: RoomEntity)

    suspend fun deleteSearch(imageUri: String)
}