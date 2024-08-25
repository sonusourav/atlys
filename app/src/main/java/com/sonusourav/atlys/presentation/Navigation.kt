package com.sonusourav.atlys.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sonusourav.atlys.presentation.details.MovieDetailsScreen
import com.sonusourav.atlys.presentation.search.SearchPageScreen
import com.sonusourav.atlys.presentation.trending.TrendingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Trending.route) {

        // Trending Screen
        composable(route = Screen.Trending.route) {
            TrendingScreen(navController = navController)
        }

        // Search Page Screen
        composable(
            route = Screen.SearchPageScreen.route
        ) {
            SearchPageScreen(navController = navController)
        }

        // Movie Details Screen
        composable(
            route = Screen.MovieDetailsScreen.route + "?movieId={movieId}&moviesTitle={moviesTitle}",
            arguments = listOf(
                navArgument(name = "movieId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            MovieDetailsScreen(navController = navController)
        }

    }
}