package com.example.taskgroup.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.taskgroup.R
import com.example.taskgroup.data.models.Product

@Composable
fun LoadingView() {
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
fun ErrorView(message: String?, onRetryButtonPressed: () -> Unit) {
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
            onClick = onRetryButtonPressed
        ) {
            Text("Retry")
        }
    }
}
@Composable
fun ProductItem(product: Product, navController: NavHostController) {
    OutlinedCard(
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set("id", product.id)
            navController.navigate(Screen.Detail.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(0.5.dp, Color.Black),
    ) {
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
}