package com.example.taskgroup.uis

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.taskgroup.data.models.Product
import com.example.taskgroup.utils.ErrorView
import com.example.taskgroup.utils.LoadingView
import com.example.taskgroup.utils.ProductItem
import com.example.taskgroup.utils.Screen
import com.example.taskgroup.utils.State
import com.example.taskgroup.viewmodels.ProductsByCategoriesViewModel

@Composable
fun ProductsInCategoriesScreen(navController: NavHostController) {
    val id = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("id")!!
    val viewModel: ProductsByCategoriesViewModel = viewModel()
    LaunchedEffect(id) {
        viewModel.getProductsByCategory(id)
    }
    val state = viewModel.productState.collectAsState()
    when (val res = state.value) {
        is State.Error -> ErrorView(res.message) {
            viewModel.getProductsByCategory(viewModel.currCategoryID)
        }

        State.Loading -> LoadingView()
        is State.Success<List<Product>> -> ProductsInCategoriesSuccess(res.data, navController)
    }

}

@Composable
fun ProductsInCategoriesSuccess(products: List<Product>, navController: NavHostController) {
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


