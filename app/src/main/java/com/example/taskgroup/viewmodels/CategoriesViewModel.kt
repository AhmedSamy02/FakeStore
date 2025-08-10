package com.example.taskgroup.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskgroup.data.apis.RetrofitInstance
import com.example.taskgroup.data.models.Category
import com.example.taskgroup.data.models.Product
import com.example.taskgroup.data.repos.CategoriesRepo
import com.example.taskgroup.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val repo = CategoriesRepo(RetrofitInstance.getInstance())
    private val _categoriesState = MutableStateFlow<State<List<Category>>>(State.Loading)
    val categoriesState get() = _categoriesState
    private val _productsState = MutableStateFlow<State<List<Product>>>(State.Loading)
    val productState get() = _productsState
    init {
        getCategories()
    }
    fun getCategories() {
        viewModelScope.launch {
            repo.getCategories().collect { state ->
                _categoriesState.value = state
            }
        }
    }

    fun getProductsByCategory(id: Int) {
        viewModelScope.launch {
            repo.getProductsByCategory(id).collect { state ->
                _productsState.value = state
            }
        }
    }
}