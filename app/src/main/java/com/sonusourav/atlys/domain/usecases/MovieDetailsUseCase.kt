package com.sonusourav.atlys.domain.usecases

import com.sonusourav.atlys.utils.NetworkResult
import com.sonusourav.atlys.data.model.MovieDetailResponse
import com.sonusourav.atlys.data.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MovieDetailsUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(lang: String, movieId: String) : Flow<NetworkResult<Response<MovieDetailResponse>>> {
        return movieRepository.movieDetails(lang, movieId)
    }
}