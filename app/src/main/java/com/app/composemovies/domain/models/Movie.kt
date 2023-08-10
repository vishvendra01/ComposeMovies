package com.app.composemovies.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val overview: String,
    val voteAverage: Double,
    val releaseDate: String
)