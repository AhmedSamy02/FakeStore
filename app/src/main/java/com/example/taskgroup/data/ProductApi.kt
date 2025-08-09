package com.example.taskgroup.data

import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<Product>

    companion object {
        fun create(): ProductApi {
            val contentType = "application/json".toMediaType()
            val json = Json {
                ignoreUnknownKeys = true
            }
            return Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(json.asConverterFactory(contentType))
                .build()
                .create(ProductApi::class.java)
        }
    }
}
