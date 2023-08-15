package com.app.composemovies.domain.usecase

import com.app.composemovies.domain.models.Movie
import com.app.composemovies.domain.repository.MovieRepository
import com.app.composemovies.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface PopularMoviesUseCase {
    suspend fun getPopularMovies(page: Int): Flow<NetworkResponse<List<Movie>>>
}

class PopularMoviesUseCaseImpl(
    private val repository: MovieRepository,
) : PopularMoviesUseCase {
    override suspend fun getPopularMovies(page: Int): Flow<NetworkResponse<List<Movie>>> {
        return repository.getPopularMovies(page = page)
    }
}