package com.example.taskgroup.uis

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.taskgroup.data.models.Product
import com.example.taskgroup.utils.ErrorView
import com.example.taskgroup.utils.LoadingView
import com.example.taskgroup.utils.ProductItem
import com.example.taskgroup.utils.State
import com.example.taskgroup.viewmodels.ProductViewModel


@Composable
fun ProductListScreen(navController: NavHostController) {
    val viewModel: ProductViewModel = viewModel()
    val state = viewModel.products.collectAsState()
    when (val res = state.value) {
        is State.Error -> ErrorView(res.message) {
            viewModel.fetchProducts()
        }
        State.Loading -> LoadingView()
        is State.Success<List<Product>> -> ProductListScreenSuccess(res.data,navController)
    }

}

@Composable
fun ProductListScreenSuccess(products: List<Product>, navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product, navController)
        }
    }
}
