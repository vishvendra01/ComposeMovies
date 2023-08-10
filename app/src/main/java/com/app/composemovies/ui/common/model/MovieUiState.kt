package com.app.composemovies.ui.common.model

data class MovieUiState(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val overview: String,
    val voteAverage: Double,
    val releaseDate: String
)