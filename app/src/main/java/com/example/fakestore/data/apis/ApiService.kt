package com.example.fakestore.data.apis

import com.example.fakestore.data.models.Category
import com.example.fakestore.data.models.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(
        @Query("offset")
        offset: Int,
        @Query("limit")
        limit: Int,
    ): Response<List<Product>>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: String): Response<Product>

    @GET("categories")
    suspend fun getCategories(): Response<List<Category>>

    @GET("categories/{id}/products")
    suspend fun getProductsByCategory(@Path("id") id: Int): Response<List<Product>>
}