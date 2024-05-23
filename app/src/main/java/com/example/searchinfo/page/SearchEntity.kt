package com.example.searchinfo.page

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchEntity(
    val collection: String,
    val thumbnailurl: String,
    val imageurl: String,
    val datetime: String,
): Parcelable
