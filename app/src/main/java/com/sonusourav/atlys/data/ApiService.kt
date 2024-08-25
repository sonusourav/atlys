package com.sonusourav.atlys.data

import com.sonusourav.atlys.data.model.details.MovieDetailsResponse
import com.sonusourav.atlys.data.model.movies.TrendingMoviesResponse
import com.sonusourav.atlys.data.model.search_movies.SearchMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): TrendingMoviesResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("language") language: String?,
    ): Response<MovieDetailsResponse>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String?,
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): Response<SearchMovieResponse>
}