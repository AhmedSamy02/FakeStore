package com.example.taskgroup.data
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryApi {
    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("categories/{id}/products")
    suspend fun getProductsByCategory(
        @Path("id") categoryId: Int,
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): List<Product>
}
