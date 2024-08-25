package com.sonusourav.atlys.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sonusourav.atlys.R
import com.sonusourav.atlys.data.model.MovieDetailResponse
import com.sonusourav.atlys.presentation.details.components.ToolBar
import com.sonusourav.atlys.presentation.trending.components.ErrorView
import com.sonusourav.atlys.presentation.trending.components.Loader
import com.sonusourav.atlys.utils.Constants.ORIGINAL_IMAGE_URL

@Composable
fun MovieDetailsScreen(
    navController: NavController
) {
    val viewModel: MovieDetailsViewModel = hiltViewModel()

    Scaffold(topBar = {
        ToolBar(onBack = {
            navController.popBackStack()
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues.calculateBottomPadding())
        ) {
            val details = viewModel.movieDetailsResponse.value

            if (details.movieId != null) {
                LazyColumn(modifier = Modifier.padding(vertical = 5.dp), content = {
                    item { ItemPoster(details) }
                    item { ItemTitle(details) }
                    item { ItemDescription(details) }
                })
            }
            Loader(isLoading = viewModel.isLoading.value)
            ErrorView(viewModel.apiError.value)
        }
    }
}


@Composable
fun ItemPoster(response: MovieDetailResponse) {
    Box(modifier = Modifier.padding(horizontal = 15.dp)) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(ORIGINAL_IMAGE_URL + response.posterPath).crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.description),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(360.dp)
                .wrapContentWidth()
                .clip(shape = RoundedCornerShape(10.dp))
        )
    }
}

@Composable
fun ItemTitle(
    response: MovieDetailResponse,
) {

    Spacer(modifier = Modifier.height(20.dp))

    val title = response.title ?: ""
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Start
    )

}

@Composable
fun ItemDescription(response: MovieDetailResponse) {
    Spacer(modifier = Modifier.height(15.dp))

    val lineHeight = MaterialTheme.typography.h6.fontSize * 4 / 3
    Text(
        text = response.overview ?: "",
        style = MaterialTheme.typography.body2,
        lineHeight = lineHeight,
        modifier = Modifier.padding(horizontal = 15.dp)
    )
}


