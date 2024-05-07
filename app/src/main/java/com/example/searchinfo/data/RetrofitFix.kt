package com.example.searchinfo.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFix {
    private val retrofit : Retrofit? =
        Retrofit.Builder()
            .baseUrl(Url.KAKAO_API_KEY)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient())
            .build()

    private fun okhttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

    val retrofitResult : ProductApiService = retrofit?.create(ProductApiService::class.java)!!
}