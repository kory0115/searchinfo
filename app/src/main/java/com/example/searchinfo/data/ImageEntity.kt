package com.example.searchinfo.data

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageEntity(
    @SerializedName("collection")
    val collection: String,
    @SerializedName("thumbnail_url")
    val thumbnailurl: String,
    @SerializedName("image_url")
    val imageurl: String,
    @SerializedName("datetime")
    val datetime: String,
): Parcelable
