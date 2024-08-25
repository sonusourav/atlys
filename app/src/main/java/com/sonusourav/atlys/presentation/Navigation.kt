package com.sonusourav.atlys.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sonusourav.atlys.presentation.trending.TrendingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Trending.route) {

        // Trending Screen
        composable(route = Screen.Trending.route) {
            TrendingScreen(navController = navController)
        }

    }
}