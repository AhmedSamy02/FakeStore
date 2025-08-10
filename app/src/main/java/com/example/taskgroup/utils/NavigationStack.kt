package com.example.taskgroup.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskgroup.ProductListScreen
import com.example.taskgroup.uis.CategoriesScreen
import com.example.taskgroup.uis.ProductsInCategoriesScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Screen.Category.route
    ) {
        composable(Screen.Main.route) {
            ProductListScreen(
                navController = navController,
            )
        }
        composable(Screen.Category.route) {
            CategoriesScreen(
                navController=navController
            )
        }
        composable(Screen.ProductByCategory.route) {
            ProductsInCategoriesScreen(
                navController=navController
            )
        }
    }
}