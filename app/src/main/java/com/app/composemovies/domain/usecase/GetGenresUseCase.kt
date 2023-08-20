package com.app.composemovies.domain.usecase

import com.app.composemovies.domain.models.Genre
import com.app.composemovies.domain.repository.MovieRepository

interface GetGenresUseCase {
    suspend fun getGenres(): List<Genre>
}

class GetGenresUseCaseImpl(
    private val repository: MovieRepository
) : GetGenresUseCase {

    override suspend fun getGenres(): List<Genre> {
        return repository.getGenres()
    }
}