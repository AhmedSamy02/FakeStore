package com.example.taskgroup.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String? = null, val icon: ImageVector? = null) {
    object Main : Screen("main_screen", "Home", Icons.Default.Home)
    object Category : Screen("category_screen", "Category", Icons.Default.Face)
    object ProductByCategory : Screen("product_by_category_screen")
    object Detail : Screen("detail_screen")
    object Cart : Screen("cart_screen","Cart", Icons.Default.ShoppingCart)
}