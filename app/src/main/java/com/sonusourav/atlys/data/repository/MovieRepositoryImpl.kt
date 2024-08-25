package com.sonusourav.atlys.data.repository

import com.sonusourav.atlys.utils.NetworkResult
import com.sonusourav.atlys.utils.ResponseCodeManager
import com.sonusourav.atlys.data.ApiService
import com.sonusourav.atlys.data.model.details.MovieDetailsResponse
import com.sonusourav.atlys.data.model.movies.TrendingMoviesResponse
import com.sonusourav.atlys.data.model.search_movies.SearchMovieResponse
import com.sonusourav.atlys.domain.MovieRepository
import com.sonusourav.atlys.utils.Constants
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieRepository {

    override suspend fun trendingMoviesList(
        lang: String,
        page: Int
    ): Flow<NetworkResult<TrendingMoviesResponse>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getTrendingMovies(
                    page = page, language = lang
                )
                emit(
                    NetworkResult.Success(response)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()?.let { ResponseCodeManager.checkRetrofitApiResponse(it) })
                        }

                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }

                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun movieDetails(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieDetailsResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getMovieDetails(
                    movieId = movieId, language = lang
                )
                emit(
                    if (response.isSuccessful) NetworkResult.Success(response) else
                        NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()
                                    ?.let { ResponseCodeManager.checkRetrofitApiResponse(it) })
                        }

                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }

                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun searchMovies(
        query: String,
        lang: String
    ): Flow<NetworkResult<Response<SearchMovieResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.searchMovie(query = query, language = lang, page = 1)
                emit(
                    if (response.isSuccessful) NetworkResult.Success(response) else
                        NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()
                                    ?.let { ResponseCodeManager.checkRetrofitApiResponse(it) })
                        }

                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }

                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }


}
