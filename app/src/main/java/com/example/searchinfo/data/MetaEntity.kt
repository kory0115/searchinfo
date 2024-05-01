package com.example.searchinfo.data

import com.google.gson.annotations.SerializedName

data class MetaEntity(
    @SerializedName("total_count")
    val totalcount : Int,
    @SerializedName("pageable_count")
    val pageablecount: Int,
    @SerializedName("is_end")
    val isend: Boolean
)