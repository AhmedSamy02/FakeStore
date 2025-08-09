package com.example.taskgroup.data

class ProductRepository(private val api: ProductApi) {
    suspend fun getProducts(): List<Product> {
        return api.getProducts()
    }
}
