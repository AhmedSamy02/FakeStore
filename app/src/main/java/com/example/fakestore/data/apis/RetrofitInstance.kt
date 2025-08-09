package com.example.fakestore.data.apis

import com.example.fakestore.data.apis.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "https://api.escuelajs.co/api/v1"
    fun getInstance(): ApiService {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientHelper = OkHttpClient().newBuilder().addInterceptor(interceptor)
        val retrofit = Retrofit.Builder()
            .client(clientHelper.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}