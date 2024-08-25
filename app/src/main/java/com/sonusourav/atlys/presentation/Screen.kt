package com.sonusourav.atlys.presentation

sealed class Screen(val route:String){
    object Trending: Screen("trending_screen")
    object SearchPageScreen: Screen("search_screen")
    object MovieDetailsScreen: Screen("movie_details_screen")
}
