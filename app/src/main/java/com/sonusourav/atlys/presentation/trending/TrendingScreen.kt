package com.sonusourav.atlys.presentation.trending

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sonusourav.atlys.presentation.trending.components.MovieItemCard
import com.sonusourav.atlys.presentation.trending.components.TopBar

@Composable
fun TrendingScreen(
    navController: NavController
) {

    val viewModel: TrendingViewModel = hiltViewModel()

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(horizontal = 5.dp)) {
            val movieItems = viewModel.trendingMoviesList

            val modifier = if (viewModel.isLoading.value)
                Modifier.padding(bottom = 80.dp)
            else Modifier
                .fillMaxWidth()
                .weight(1.0f)

            TopBar(navController)

            LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(2), content = {
                items(movieItems.size) { i ->
                    Row {
                        MovieItemCard(
                            item = movieItems[i]
                        ) {
                            //navController.navigate(Screen.MovieDetailsScreen.route + "?movieId=${item?.movieId.toString()}&moviesTitle=${item?.title}")
                        }
                    }
                }
            })
        }
    }
}
