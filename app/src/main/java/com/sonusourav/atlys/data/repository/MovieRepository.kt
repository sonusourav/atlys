package com.sonusourav.atlys.data.repository

import com.sonusourav.atlys.utils.NetworkResult
import com.sonusourav.atlys.data.model.MovieDetailResponse
import com.sonusourav.atlys.data.model.MovieListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {

    suspend fun trendingMoviesList(
        lang: String,
        page: Int
    ): Flow<NetworkResult<MovieListResponse>>

    suspend fun movieDetails(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieDetailResponse>>>

    suspend fun searchMovies(
        query: String,
        lang: String
    ): Flow<NetworkResult<Response<MovieListResponse>>>
}