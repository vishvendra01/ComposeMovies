package com.app.composemovies.ui.moviedetail.model

data class MovieDetailUiState(
    val id: Int,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val releaseDate: String,
    val voteAverage: String,
    val voteCount: Int,
    val runtime: String,
    val genres: List<String>,
    val status: String,
    val tagline: String,
    val posterPath: String,
    val adult: Boolean,
    val video: Boolean,
    val imdbId: String,
    val actors: List<ActorUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val isFavorite: Boolean = false
) {
    companion object {
        val Initial = MovieDetailUiState(
            id = 0,
            title = "",
            overview = "",
            backdropPath = "",
            releaseDate = "",
            voteAverage = "",
            voteCount = 0,
            runtime = "",
            genres = emptyList(),
            actors = emptyList(),
            status = "",
            tagline = "",
            posterPath = "",
            adult = false,
            video = false,
            imdbId = ""
        )
    }
}

data class ActorUiState(
    val id: Int,
    val name: String,
    val profilePath: String,
    val character: String,
    val order: Int
)
