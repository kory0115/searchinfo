package com.example.searchinfo.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFix {
    private val retrofit : Retrofit? =
        Retrofit.Builder()
            .baseUrl(Url.KAKAO_API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val retrofitResult : ProductApiService = retrofit?.create(ProductApiService::class.java)!!
}