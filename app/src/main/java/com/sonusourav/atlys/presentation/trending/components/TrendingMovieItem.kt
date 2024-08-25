package com.sonusourav.atlys.presentation.trending.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sonusourav.atlys.R
import com.sonusourav.atlys.data.model.MovieDetailResponse
import com.sonusourav.atlys.utils.Constants.ORIGINAL_IMAGE_URL

@Composable
fun TrendingMovieItem(
    item: MovieDetailResponse,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .background(color = Color.White)
            .clickable {
                onItemClick.invoke()
            },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = modifier
        ) {

            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(ORIGINAL_IMAGE_URL + item.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.description),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(172.dp), loading = {
                    val composition by rememberLottieComposition(
                        LottieCompositionSpec.RawRes(
                            R.raw.anim_loading
                        )
                    )
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            val lineHeight = MaterialTheme.typography.h6.fontSize * 4 / 3
            Text(
                text = item.title ?: "",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 10.dp),
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                lineHeight = lineHeight
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

    }

}