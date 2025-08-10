package com.example.taskgroup.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskgroup.Cart
import com.example.taskgroup.uis.CategoriesScreen
import com.example.taskgroup.uis.ProductDetailsScreen
import com.example.taskgroup.uis.ProductListScreen
import com.example.taskgroup.uis.ProductsInCategoriesScreen

@Composable
fun NavigationStack(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            ProductListScreen(
                navController = navController,
            )
        }
        composable(Screen.Category.route) {
            CategoriesScreen(
                navController = navController
            )
        }
        composable(Screen.ProductByCategory.route) {
            ProductsInCategoriesScreen(
                navController = navController
            )
        }
        composable(Screen.Detail.route) {
            ProductDetailsScreen(
                navController = navController
            )
        }
        composable(Screen.Cart.route) {
        }
    }
}