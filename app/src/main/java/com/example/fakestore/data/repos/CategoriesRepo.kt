package com.example.fakestore.data.repos

import com.example.fakestore.data.apis.ApiService
import com.example.fakestore.data.models.Category
import com.example.fakestore.data.models.Product
import com.example.fakestore.utils.State
import com.example.fakestore.utils.callApiWithRetry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class CategoriesRepo(val service: ApiService) {
    fun getCategories(): Flow<State<List<Category>>> = callApiWithRetry { service.getCategories() }

    fun getProductsByCategory(id: Int): Flow<State<List<Product>>> =
        callApiWithRetry { service.getProductsByCategory(id) }
}