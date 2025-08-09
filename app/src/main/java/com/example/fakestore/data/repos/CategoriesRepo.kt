package com.example.fakestore.data.repos

import com.example.fakestore.data.apis.ApiService
import com.example.fakestore.data.models.Category
import com.example.fakestore.data.models.Product
import com.example.fakestore.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoriesRepo(val service: ApiService) {
    fun getCategories(): Flow<State<List<Category>>> = flow {
        emit(State.Loading)
        try {
            val response = service.getCategories()
            emit(State.Success(response.body()!!))
        } catch (e: Exception) {
            emit(
                State.Error(
                    e.localizedMessage
                )
            )
        }
    }

    fun getProductsByCategory(id: String): Flow<State<List<Product>>> = flow {
        emit(State.Loading)
        try {
            val response = service.getProductsByCategory(id)
            emit(State.Success(response.body()!!))
        } catch (e: Exception) {
            emit(
                State.Error(
                    e.localizedMessage
                )
            )
        }
    }
}