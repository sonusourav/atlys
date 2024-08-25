package com.sonusourav.atlys.domain.usecases

import com.sonusourav.atlys.domain.usecases.details.MovieDetails
import com.sonusourav.atlys.domain.usecases.search_movie.SearchMoviesPagingList
import com.sonusourav.atlys.domain.usecases.trending.TrendingMoviesList

data class UseCases(
    val trendingMoviesList: TrendingMoviesList,
    val movieDetails: MovieDetails,
    val searchMoviesPagingList: SearchMoviesPagingList,
)