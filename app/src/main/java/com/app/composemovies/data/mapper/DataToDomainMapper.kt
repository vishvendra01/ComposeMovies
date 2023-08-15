package com.app.composemovies.data.mapper

import com.app.composemovies.data.models.CastResult
import com.app.composemovies.data.models.MovieDetailResponse
import com.app.composemovies.data.models.MovieResult
import com.app.composemovies.domain.models.Cast
import com.app.composemovies.domain.models.Genre
import com.app.composemovies.domain.models.Movie
import com.app.composemovies.domain.models.MovieDetail

const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"
const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w1280"
const val CAST_BASE_URL = "https://image.tmdb.org/t/p/w185"

class DataToDomainMapper {

    fun map(input: MovieResult): Movie {
        return Movie(
            id = input.id,
            title = input.title,
            posterPath = "$POSTER_BASE_URL${input.posterPath}",
            backdropPath = "$BACKDROP_BASE_URL${input.backdropPath}",
            overview = input.overview,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate
        )
    }

    fun map(input: MovieDetailResponse): MovieDetail {
        return MovieDetail(
            id = input.id,
            title = input.title,
            posterPath = "$POSTER_BASE_URL${input.poster_path}",
            backdropPath = "$BACKDROP_BASE_URL${input.backdrop_path}",
            overview = input.overview,
            voteAverage = input.vote_average,
            releaseDate = input.release_date,
            genres = input.genres.map { Genre(it.id, it.name) },
            runtime = input.runtime,
            status = input.status,
            tagline = input.tagline,
            voteCount = input.vote_count,
            video = input.video,
            adult = input.adult,
            imdbId = input.imdb_id,
        )
    }

    fun map(input: CastResult): Cast {
        return Cast(
            id = input.id,
            name = input.name,
            profilePath = "$CAST_BASE_URL${input.profilePath}",
            character = input.character,
            order = input.order
        )
    }
}
