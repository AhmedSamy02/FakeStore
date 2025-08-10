package com.example.taskgroup.data.repos

import com.example.taskgroup.data.apis.ApiService
import com.example.taskgroup.data.models.Product
import com.example.taskgroup.utils.State
import com.example.taskgroup.utils.callApiWithRetry
import kotlinx.coroutines.flow.Flow

class ProductRepo(val service: ApiService) {
    fun getProducts(offset: Int, limit: Int): Flow<State<List<Product>>> =
        callApiWithRetry<List<Product>> { service.getProducts(offset, limit) }

    fun getProduct(id: String): Flow<State<Product>> =
        callApiWithRetry<Product> { service.getProduct(id) }

}