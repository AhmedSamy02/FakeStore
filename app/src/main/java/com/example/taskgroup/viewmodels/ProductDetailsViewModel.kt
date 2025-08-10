package com.example.taskgroup.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskgroup.data.apis.ApiService
import com.example.taskgroup.data.apis.RetrofitInstance
import com.example.taskgroup.data.models.Product
import com.example.taskgroup.data.repos.CategoriesRepo
import com.example.taskgroup.data.repos.ProductRepo
import com.example.taskgroup.utils.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
) : ViewModel() {
    private val productRepo: ProductRepo = ProductRepo(RetrofitInstance.getInstance())
    private val _uiState = MutableStateFlow(ProductDetailsUiState())
    val uiState: StateFlow<ProductDetailsUiState> = _uiState.asStateFlow()

    fun loadProductDetails(productId: String) {
        viewModelScope.launch {
            productRepo.getProduct(productId).collect { state ->
                when (state) {
                    is State.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                    is State.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            product = state.data,
                            error = null
                        )
                    }
                    is State.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = state.message
                        )
                    }
                }
            }
        }
    }

    fun addToCart(product: Product) {
        // TODO: Implement cart functionality

        _uiState.value = _uiState.value.copy(
            showAddedToCartMessage = true
        )
    }

    fun clearAddedToCartMessage() {
        _uiState.value = _uiState.value.copy(
            showAddedToCartMessage = false
        )
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(
            error = null
        )
    }
}

data class ProductDetailsUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null,
    val showAddedToCartMessage: Boolean = false
)