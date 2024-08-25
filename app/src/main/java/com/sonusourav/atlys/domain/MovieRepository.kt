package com.sonusourav.atlys.domain

import com.sonusourav.atlys.utils.NetworkResult
import com.sonusourav.atlys.data.model.details.MovieDetailsResponse
import com.sonusourav.atlys.data.model.movies.TrendingMoviesResponse
import com.sonusourav.atlys.data.model.search_movies.SearchMovieResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {

    suspend fun trendingMoviesList(
        lang: String,
        page: Int
    ): Flow<NetworkResult<TrendingMoviesResponse>>

    suspend fun movieDetails(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieDetailsResponse>>>


    suspend fun searchPagingList(
        query: String,
        lang: String
    ): Flow<NetworkResult<Response<SearchMovieResponse>>>
}