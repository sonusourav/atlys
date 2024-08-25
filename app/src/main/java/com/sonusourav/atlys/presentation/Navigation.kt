package com.sonusourav.atlys.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sonusourav.atlys.presentation.NavigationConstants.MOVIE_ID
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
            route = Screen.MovieDetailsScreen.route + "?$MOVIE_ID={$MOVIE_ID}",
            arguments = listOf(
                navArgument(name = MOVIE_ID) {
                    type = NavType.StringType
                    defaultValue = "1"
                }
            )
        ) {
            MovieDetailsScreen(navController = navController)
        }

    }
}

object NavigationConstants {
    const val MOVIE_ID = "movieId"
}