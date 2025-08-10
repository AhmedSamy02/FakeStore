package com.example.taskgroup.data.repos

import com.example.taskgroup.data.apis.ApiService
import com.example.taskgroup.data.models.Category
import com.example.taskgroup.data.models.Product
import com.example.taskgroup.utils.State
import com.example.taskgroup.utils.callApiWithRetry
import kotlinx.coroutines.flow.Flow

class CategoriesRepo(val service: ApiService) {
    fun getCategories(): Flow<State<List<Category>>> = callApiWithRetry { service.getCategories() }

    fun getProductsByCategory(id: Int): Flow<State<List<Product>>> =
        callApiWithRetry { service.getProductsByCategory(id) }
}