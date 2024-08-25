package com.sonusourav.atlys.domain.usecases.details

import com.sonusourav.atlys.utils.NetworkResult
import com.sonusourav.atlys.data.model.details.MovieDetailsResponse
import com.sonusourav.atlys.domain.MovieRepository
import javax.inject.Inject
import retrofit2.Response

class MovieDetailsUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(lang: String, movieId: String) :kotlinx.coroutines.flow.Flow<NetworkResult<Response<MovieDetailsResponse>>> {
        return movieRepository.movieDetails(lang, movieId)
    }
}