package com.app.composemovies.domain.usecase

import com.app.composemovies.domain.models.Movie
import com.app.composemovies.domain.repository.MovieRepository

interface GetMoviesByGenresUseCase {
    suspend fun getMoviesByGenres(genreId: Int, page: Int): List<Movie>
}

class GetMoviesByGenresUseCaseImpl(
    private val movieRepository: MovieRepository
) : GetMoviesByGenresUseCase {

    override suspend fun getMoviesByGenres(genreId: Int, page: Int): List<Movie> {
        return movieRepository.getMoviesByGenre(genreId, page)
    }
}