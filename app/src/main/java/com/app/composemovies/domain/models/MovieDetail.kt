package com.app.composemovies.domain.models

data class MovieDetail(
    val adult: Boolean,
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val overview: String,
    val voteAverage: Float,
    val releaseDate: String,
    val genres: List<Genre>,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val voteCount: Int,
    val imdbId: String,
    val video: Boolean,
)

data class Genre(
    val id: Int,
    val name: String
)
