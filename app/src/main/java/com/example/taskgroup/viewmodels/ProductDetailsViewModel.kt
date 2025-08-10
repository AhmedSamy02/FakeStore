package com.example.taskgroup.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskgroup.Room.CartProduct
import com.example.taskgroup.Room.CartRepo
import com.example.taskgroup.Room.DAO
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
    private val productRepo: ProductRepo,
    private val dao: DAO
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailsUiState())
    val uiState: StateFlow<ProductDetailsUiState> = _uiState.asStateFlow()

    fun loadProductDetails(productId: String) {
        viewModelScope.launch {
            productRepo.getProduct(productId).collect { state ->
                when (state) {
                    is State.Loading -> _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                    is State.Success -> _uiState.value = _uiState.value.copy(isLoading = false, product = state.data, error = null)
                    is State.Error -> _uiState.value = _uiState.value.copy(isLoading = false, error = state.message)
                }
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            val cartProduct = CartProduct(
                id = product.id,
                title = product.title,
                price = product.price.toDouble(),
                description = product.description,
                category = product.category.name, // or however you store it
                image = product.images.firstOrNull() ?: "",
                quantity = 1
            )
            dao.upsertProduct(cartProduct)
            _uiState.value = _uiState.value.copy(showAddedToCartMessage = true)
        }
    }

    fun clearAddedToCartMessage() {
        _uiState.value = _uiState.value.copy(showAddedToCartMessage = false)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}


data class ProductDetailsUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null,
    val showAddedToCartMessage: Boolean = false
)