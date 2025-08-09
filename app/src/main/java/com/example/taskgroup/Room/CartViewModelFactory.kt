package com.example.taskgroup.Room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskgroup.appui.CartViewModel

class CartViewModelFactory(private val dao: DAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}