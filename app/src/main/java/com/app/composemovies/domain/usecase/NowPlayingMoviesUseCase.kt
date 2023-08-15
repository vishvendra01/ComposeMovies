package com.app.composemovies.domain.usecase

import com.app.composemovies.domain.models.Movie
import com.app.composemovies.domain.repository.MovieRepository
import com.app.composemovies.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface NowPlayingMoviesUseCase {
    suspend fun getPopularMovies(page: Int): Flow<NetworkResponse<List<Movie>>>
}

class NowPlayingMoviesUseCaseImpl(
    private val repository: MovieRepository
) : NowPlayingMoviesUseCase {
    override suspend fun getPopularMovies(page: Int): Flow<NetworkResponse<List<Movie>>> {
        return repository.getNowPlayingMovies(page)
    }
}