package com.sonusourav.atlys.data.model.search_movies

import com.google.gson.annotations.SerializedName
import com.sonusourav.atlys.data.model.movies.MovieItem

data class SearchMovieResponse(

	@SerializedName("page")
	val page: Int? = null,

	@SerializedName("total_pages")
	val totalPages: Int? = null,

	@SerializedName("results")
	val results: List<MovieItem>? = null,

	@SerializedName("total_results")
	val totalResults: Int? = null
)
