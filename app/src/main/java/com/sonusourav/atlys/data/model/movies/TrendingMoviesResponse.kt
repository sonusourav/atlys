package com.sonusourav.atlys.data.model.movies

import com.google.gson.annotations.SerializedName

data class TrendingMoviesResponse(

    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("total_pages")
    val totalPages: Int? = null,

    @SerializedName("results")
    val results: List<MovieItem>? = null,

    @SerializedName("total_results")
    val totalResults: Int? = null
)