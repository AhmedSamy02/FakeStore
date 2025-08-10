package com.example.fakestore.data.repos

import com.example.fakestore.data.apis.ApiService
import com.example.fakestore.data.models.Product
import com.example.fakestore.utils.State
import com.example.fakestore.utils.callApiWithRetry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepo(val service: ApiService) {
    fun getProducts(offset: Int, limit: Int): Flow<State<List<Product>>> =
        callApiWithRetry<List<Product>> { service.getProducts(offset, limit) }

    fun getProduct(id: String): Flow<State<Product>> =
        callApiWithRetry<Product> { service.getProduct(id) }

}