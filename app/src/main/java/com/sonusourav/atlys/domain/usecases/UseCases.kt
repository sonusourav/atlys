package com.sonusourav.atlys.domain.usecases

import com.sonusourav.atlys.domain.usecases.details.MovieDetailsUseCase
import com.sonusourav.atlys.domain.usecases.search_movie.SearchMoviesUsecase
import com.sonusourav.atlys.domain.usecases.trending.TrendingMoviesUseCase

data class UseCases(
    val trendingMoviesUseCase: TrendingMoviesUseCase,
    val movieDetails: MovieDetailsUseCase,
    val searchMovies: SearchMoviesUsecase,
)