package com.example.taskgroup.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskgroup.data.apis.RetrofitInstance
import com.example.taskgroup.data.repos.ProductRepo
import com.example.taskgroup.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.example.taskgroup.data.models.Product

class ProductViewModel : ViewModel() {
    private val repo = ProductRepo(RetrofitInstance.getInstance())
    private val _productsState = MutableStateFlow<State<List<Product>>>(State.Loading)
    val products get() = _productsState
    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            repo.getProducts(0,2000).collect { state ->
                _productsState.value = state
            }
        }
    }
}