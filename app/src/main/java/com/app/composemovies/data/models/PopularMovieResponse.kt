package com.app.composemovies.data.models

import com.google.gson.annotations.SerializedName


data class  PopularMovieResponse(
    val page: Int,
    @SerializedName("results")
    val movieResults: List<MovieResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)