package com.example.taskgroup.appui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskgroup.data.Product
import com.example.taskgroup.data.ProductApi
import com.example.taskgroup.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository(ProductApi.create())

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                _products.value = repository.getProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}