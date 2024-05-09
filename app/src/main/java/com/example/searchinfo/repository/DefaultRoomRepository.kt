package com.example.searchinfo.repository

import com.example.searchinfo.room.RoomEntity
import com.example.searchinfo.room.SearchDao
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DefaultRoomRepository(
    private val searchDao: SearchDao
) : RoomRepository{
    override suspend fun getAll(): List<RoomEntity> {
        return searchDao.getAll()
    }

    override suspend fun saveSearch(roomEntity: RoomEntity) {
        searchDao.saveSearch(roomEntity)
    }

    override suspend fun deleteSearch(imageUri: String) {
        searchDao.deleteSearch(imageUri)
    }
}