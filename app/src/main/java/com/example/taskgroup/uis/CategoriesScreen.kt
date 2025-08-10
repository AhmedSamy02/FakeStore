package com.example.taskgroup.uis

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.taskgroup.data.models.Category
import com.example.taskgroup.utils.ErrorView
import com.example.taskgroup.utils.LoadingView
import com.example.taskgroup.utils.Screen
import com.example.taskgroup.viewmodels.CategoriesViewModel
import com.example.taskgroup.utils.State
import com.example.taskgroup.viewmodels.ProductsByCategoriesViewModel


@Composable
fun CategoriesScreen(navController: NavHostController) {
    val viewModel: CategoriesViewModel = viewModel()
    val state = viewModel.categoriesState.collectAsState()
    when (val res = state.value) {
        is State.Error -> ErrorView(res.message) {
            viewModel.getCategories()
        }
        State.Loading -> LoadingView()
        is State.Success<List<Category>> -> {
            CategoriesSuccessView(res.data, navController)
        }
    }
}

@Composable
fun CategoriesItem(
    category: Category,
    navController: NavHostController,
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set("id", category.id)
            navController.navigate(Screen.ProductByCategory.route)
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = category.image,
                contentDescription = "Category Image ${category.id}",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                onError = { e ->
                    Log.e("FakeStoreApp", "e = $e")
                }
            )
            Text(
                category.name,
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 8.dp, end = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                minLines = 2,
                maxLines = 2,
                textAlign = TextAlign.Center
            )

        }
    }
}


@Composable
fun CategoriesSuccessView(
    categories: List<Category>,
    navController: NavHostController,
) {
    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(categories) { category ->
            CategoriesItem(category, navController)
        }
    }
}