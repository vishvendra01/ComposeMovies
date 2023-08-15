package com.app.composemovies.domain.usecase

import com.app.composemovies.domain.models.MovieDetail
import com.app.composemovies.domain.repository.MovieRepository

interface MovieDetailUseCase {
    suspend fun getMovieDetail(movieId: Int): MovieDetail
}

class MovieDetailUseCaseImpl(
    private val repository: MovieRepository,
) : MovieDetailUseCase {
    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return repository.getMovieDetail(movieId)
    }
}