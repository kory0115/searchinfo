package com.example.searchinfo.data

import com.google.gson.annotations.SerializedName


data class ImageResponse(
    @SerializedName("meta")
    val meta: MetaEntity,
    @SerializedName("documents")
    val items : List<ImageEntity>
) {
}