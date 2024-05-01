package com.example.searchinfo.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "search" )
data class RoomEntity(
    val collection: String,
    @PrimaryKey val thumbnailurl: String,
    val datetime: String,
)
