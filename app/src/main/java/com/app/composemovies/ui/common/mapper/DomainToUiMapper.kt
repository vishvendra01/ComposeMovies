package com.app.composemovies.ui.common.mapper

import com.app.composemovies.domain.models.Cast
import com.app.composemovies.domain.models.Movie
import com.app.composemovies.domain.models.MovieDetail
import com.app.composemovies.ui.common.model.MovieUiState
import com.app.composemovies.ui.moviedetail.model.ActorUiState
import com.app.composemovies.ui.moviedetail.model.MovieDetailUiState

class DomainToUiMapper {

    fun map(input: Movie): MovieUiState {
        return MovieUiState(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            overview = input.overview,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate
        )
    }

    fun map(input: MovieDetail, actors: List<Cast>): MovieDetailUiState {
        return MovieDetailUiState(
            id = input.id,
            title = input.title,
            overview = input.overview,
            backdropPath = input.backdropPath,
            releaseDate = formatReleaseDate(input.releaseDate),
            voteAverage = formatVoteAverage(input.voteAverage),
            voteCount = input.voteCount,
            runtime = formatRuntime(input.runtime),
            genres = input.genres.map { it.name },
            status = input.status,
            tagline = input.tagline,
            posterPath = input.posterPath,
            adult = input.adult,
            video = input.video,
            imdbId = input.imdbId,
            actors = actors.map { map(it) }
        )
    }

    fun map(input: Cast): ActorUiState {
        return ActorUiState(
            id = input.id,
            name = input.name,
            profilePath = input.profilePath,
            character = input.character,
            order = input.order
        )
    }

    private fun formatRuntime(runtime: Int): String {
        val hours = runtime / 60
        val minutes = runtime % 60
        return "$hours h $minutes min"
    }

    private fun formatVoteAverage(voteAverage: Float): String {
        return String.format("%.1f", voteAverage)
    }

    private fun formatReleaseDate(releaseDate: String): String {
        val date = releaseDate.split("-")
        return "${date[2]} ${getMonth(date[1])} ${date[0]}"
    }

    private fun getMonth(month: String): String {
        return when (month) {
            "01" -> "Jan"
            "02" -> "Feb"
            "03" -> "Mar"
            "04" -> "Apr"
            "05" -> "May"
            "06" -> "Jun"
            "07" -> "Jul"
            "08" -> "Aug"
            "09" -> "Sep"
            "10" -> "Oct"
            "11" -> "Nov"
            "12" -> "Dec"
            else -> ""
        }
    }
}