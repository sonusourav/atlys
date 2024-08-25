package com.sonusourav.atlys.domain.usecases.trending

import com.sonusourav.atlys.utils.NetworkResult
import com.sonusourav.atlys.data.model.movies.TrendingMoviesResponse
import com.sonusourav.atlys.domain.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TrendingMoviesList @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(
        lang: String,
        page: Int
    ): Flow<NetworkResult<TrendingMoviesResponse>> {
        return movieRepository.trendingMoviesList(lang, page)
    }
}