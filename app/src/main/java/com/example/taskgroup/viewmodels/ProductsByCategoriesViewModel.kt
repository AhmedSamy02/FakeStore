package com.example.taskgroup.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskgroup.data.apis.RetrofitInstance
import com.example.taskgroup.data.models.Product
import com.example.taskgroup.data.repos.CategoriesRepo
import com.example.taskgroup.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductsByCategoriesViewModel : ViewModel() {
    private val repo = CategoriesRepo(RetrofitInstance.getInstance())
    private val _productsState = MutableStateFlow<State<List<Product>>>(State.Loading)
    val productState get() = _productsState
    var currCategoryID = 0
    fun getProductsByCategory(id: Int) {
        currCategoryID = id
        viewModelScope.launch {
            repo.getProductsByCategory(id).collect { state ->
                _productsState.value = state
            }
        }
    }
}