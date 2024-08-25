package com.sonusourav.atlys.domain.usecases

import com.sonusourav.atlys.data.model.MovieListResponse
import com.sonusourav.atlys.utils.NetworkResult
import com.sonusourav.atlys.data.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TrendingMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(
        lang: String,
        page: Int
    ): Flow<NetworkResult<MovieListResponse>> {
        return movieRepository.trendingMoviesList(lang, page)
    }
}