package com.example.taskgroup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.taskgroup.uis.BottomBar
import com.example.taskgroup.utils.NavigationStack
import com.example.taskgroup.utils.Screen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val navState = navController.currentBackStackEntryAsState()
                val hiddenBottomBarList = listOf(Screen.Detail.route)
                Scaffold(
                    containerColor = Color.White,
                    bottomBar = {
                        val currRoute = navState.value?.destination?.route
                        if (currRoute !in hiddenBottomBarList) {
                            BottomBar(navController)
                        }
                    }) { paddingValues ->
                    Box(
                        modifier = Modifier.padding(paddingValues),
                    ) { NavigationStack(navController) }

                }
            }
        }
    }
}
