package com.sonusourav.atlys.domain.usecases.search_movie

import com.sonusourav.atlys.utils.NetworkResult
import com.sonusourav.atlys.data.model.search_movies.SearchMovieResponse
import com.sonusourav.atlys.domain.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class SearchMoviesUsecase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(
        query: String,
        lang: String
    ): Flow<NetworkResult<Response<SearchMovieResponse>>> {
        return movieRepository.searchMovies(query = query, lang = lang)
    }
}