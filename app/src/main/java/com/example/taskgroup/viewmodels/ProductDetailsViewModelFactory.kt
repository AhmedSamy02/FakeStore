package com.example.taskgroup.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskgroup.Room.CartRepo
import com.example.taskgroup.Room.DAO
import com.example.taskgroup.data.repos.ProductRepo

class ProductDetailsViewModelFactory(
    private val productRepo: ProductRepo,
    private val dao: DAO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductDetailsViewModel(productRepo, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
