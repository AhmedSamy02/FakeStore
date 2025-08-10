package com.example.taskgroup.uis

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.taskgroup.R
import com.example.taskgroup.data.models.Category
import com.example.taskgroup.viewmodels.CategoriesViewModel
import com.example.taskgroup.utils.State


@Composable
fun CategoriesScreen() {
    val viewModel: CategoriesViewModel = viewModel()
    val state = viewModel.categoriesState.collectAsState()
    when (val res = state.value) {
        is State.Error -> CategoriesErrorView(res.message, viewModel)
        State.Loading -> CategoriesLoadingView()
        is State.Success<List<Category>> -> CategoriesSuccessView(res.data)
    }
}

@Composable
fun CategoriesItem(category: Category) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Green),
        onClick = {
            TODO("Add Navigation to products by details")
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
                onError = {e->
                    Log.e("FakeStoreApp","e = $e")
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
fun CategoriesLoadingView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            color = Color.Black, modifier = Modifier.size(36.dp)
        )
        Text(
            text = "Loading...",
            modifier = Modifier.padding(top = 24.dp),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Please wait while your data is loading",
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 18.sp
        )
    }
}

@Composable
fun CategoriesErrorView(message: String?, viewModel: CategoriesViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_error_outline_24),
            contentDescription = "Error",
            tint = Color.Red
        )
        Text(
            text = "Error occurred",
            modifier = Modifier.padding(top = 24.dp),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = message ?: "Unknown Error",
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 18.sp
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                viewModel.getCategories()
            }) {
            Text("Retry")
        }
    }
}

@Composable
fun CategoriesSuccessView(categories: List<Category>) {
    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(categories) { category ->
            CategoriesItem(category)
        }
    }
}