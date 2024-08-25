package com.sonusourav.atlys.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sonusourav.atlys.presentation.search.components.SearchBar
import com.sonusourav.atlys.presentation.search.components.SearchEmpty
import com.sonusourav.atlys.presentation.search.components.SearchMovieItemCard
import com.sonusourav.atlys.presentation.trending.components.ErrorView
import com.sonusourav.atlys.presentation.trending.components.Loader

@Composable
fun SearchPageScreen(
    navController: NavController,
    viewModel: SearchPageViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SearchBar(
                    onSearch = { state ->
                        state.value.text.let { query ->
                            if (query.isNotBlank()) {
                                viewModel.searchMovie(query)
                            }
                        }
                        viewModel.searchMovie(state.value.text)
                    },
                    onCancel = { viewModel.clearSearch() })
            }
        }

        Box(modifier = Modifier.padding(top = 10.dp)) {
            SearchItemList(viewModel, navController)
            Loader(isLoading = viewModel.isLoading.value)
            ErrorView(viewModel.apiError.value)
            SearchEmpty(viewModel.listEmpty.value)
        }
    }
}

@Composable
fun SearchItemList(viewModel: SearchPageViewModel, navController: NavController) {
    LazyColumn {
        items(
            items = viewModel.searchMoviePagingItems,
            key = { item ->
                item.movieId.toString()
            }
        ) { item ->
            SearchMovieItemCard(item, navController)
        }
    }
}


