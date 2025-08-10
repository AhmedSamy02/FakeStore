package com.example.taskgroup.uis

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.taskgroup.data.models.Product
import com.example.taskgroup.utils.ErrorView
import com.example.taskgroup.utils.LoadingView
import com.example.taskgroup.utils.State
import com.example.taskgroup.viewmodels.CategoriesViewModel
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
        is State.Success<List<Product>> -> ProductsInCategoriesSuccess(res.data)
    }

}

@Composable
fun ProductsInCategoriesSuccess(products: List<Product>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product)
        }
    }
}


@Composable
fun ProductItem(product: Product) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = rememberAsyncImagePainter(if (product.images.isEmpty()) "https://columbusaec.org/wp-content/uploads/2023/01/no-images-1.png" else product.images[0]),
            contentDescription = product.title,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(product.title, style = MaterialTheme.typography.titleMedium)
            Text("$${product.price}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}