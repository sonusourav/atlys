package com.sonusourav.atlys.domain.usecases

import com.sonusourav.atlys.utils.NetworkResult
import com.sonusourav.atlys.data.model.MovieListResponse
import com.sonusourav.atlys.data.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class SearchMoviesUsecase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(
        query: String,
        lang: String
    ): Flow<NetworkResult<Response<MovieListResponse>>> {
        return movieRepository.searchMovies(query = query, lang = lang)
    }
}