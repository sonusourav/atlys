package com.sonusourav.atlys.domain.usecases

data class UseCases(
    val trendingMoviesUseCase: TrendingMoviesUseCase,
    val movieDetails: MovieDetailsUseCase,
    val searchMovies: SearchMoviesUsecase,
)