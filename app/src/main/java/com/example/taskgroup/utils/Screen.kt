package com.example.taskgroup.utils

sealed class Screen(val route:String) {
    object Main: Screen("main_screen")
    object Category: Screen("category_screen")
    object ProductByCategory: Screen("product_by_category_screen")
    object Detail: Screen("detail_screen")
}