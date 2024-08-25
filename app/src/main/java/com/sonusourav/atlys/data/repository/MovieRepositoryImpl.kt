package com.sonusourav.atlys.data.repository

import com.sonusourav.atlys.utils.NetworkResult
import com.sonusourav.atlys.utils.ResponseCodeManager
import com.sonusourav.atlys.data.api.ApiService
import com.sonusourav.atlys.data.model.MovieDetailResponse
import com.sonusourav.atlys.data.model.MovieListResponse
import com.sonusourav.atlys.utils.Constants
import com.sonusourav.atlys.utils.SafeApiCall
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MovieRepository, SafeApiCall() {

    override suspend fun trendingMoviesList(
        lang: String,
        page: Int
    ): Flow<NetworkResult<MovieListResponse>> {
        return flow {
            emit(NetworkResult.Loading)
            emit(safeApiCall { apiService.getTrendingMovies(page = page, language = lang) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun movieDetails(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieDetailResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            emit(safeApiCall { apiService.getMovieDetails(movieId = movieId, language = lang) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun searchMovies(
        query: String,
        lang: String
    ): Flow<NetworkResult<Response<MovieListResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            emit(safeApiCall { apiService.searchMovie(query = query, language = lang, page = 1) })
        }.flowOn(Dispatchers.IO)
    }

}
