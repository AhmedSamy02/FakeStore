package com.example.taskgroup.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskgroup.R

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