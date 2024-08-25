package com.sonusourav.atlys.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(

	@SerializedName("page")
	val page: Int? = null,

	@SerializedName("total_pages")
	val totalPages: Int? = null,

	@SerializedName("results")
	val results: List<MovieDetailResponse>? = null,

	@SerializedName("total_results")
	val totalResults: Int? = null
)
