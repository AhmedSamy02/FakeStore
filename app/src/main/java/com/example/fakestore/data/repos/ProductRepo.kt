package com.example.fakestore.data.repos

import com.example.fakestore.data.apis.ApiService
import com.example.fakestore.data.models.Product
import com.example.fakestore.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepo(val service: ApiService) {
    fun getProducts(offset: Int, limit: Int): Flow<State<List<Product>>> = flow {
        emit(State.Loading)
        try {
            val response = service.getProducts(offset, limit)
            emit(State.Success<List<Product>>(response.body()!!))
        } catch (e: Exception) {
            emit(
                State.Error(
                    e.localizedMessage ?: "Unexpected Error please contact service provider"
                )
            )
        }
    }

    fun getProduct(id: String): Flow<State<Product>> = flow {
        emit(State.Loading)
        try {
            val response = service.getProduct(id)
            emit(State.Success<Product>(response.body()!!))
        } catch (e: Exception) {
            emit(
                State.Error(
                    e.localizedMessage ?: "Unexpected Error please contact service provider"
                )
            )
        }
    }

}