package com.sonusourav.atlys.presentation.trending

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sonusourav.atlys.presentation.NavigationConstants.MOVIE_ID
import com.sonusourav.atlys.presentation.Screen
import com.sonusourav.atlys.presentation.trending.components.ErrorView
import com.sonusourav.atlys.presentation.trending.components.TrendingMovieItem
import com.sonusourav.atlys.presentation.trending.components.TopBar

@Composable
fun TrendingScreen(
    navController: NavController
) {

    val viewModel: TrendingViewModel = hiltViewModel()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 5.dp)
        ) {
            val movieItems = viewModel.trendingMoviesList

            TopBar(navController)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f),
                contentAlignment = Alignment.Center
            ) {

                if (viewModel.isLoading.value) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp),
                        color = Color.Blue
                    )
                }

                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    content = {
                        items(movieItems.size) { i ->
                            Row {
                                TrendingMovieItem(
                                    item = movieItems[i]
                                ) {
                                    navController.navigate(Screen.MovieDetailsScreen.route + "?$MOVIE_ID=${movieItems[i].movieId.toString()}")
                                }
                            }
                        }
                    })


                ErrorView(viewModel.apiError.value)
            }
        }
    }
}
