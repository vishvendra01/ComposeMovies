package com.app.composemovies.domain.usecase

import com.app.composemovies.domain.mapper.DataToDomainMapper
import com.app.composemovies.domain.models.Movie
import com.app.composemovies.domain.repository.MovieRepository
import com.app.composemovies.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PopularMoviesUseCase {
    suspend fun getPopularMovies(page: Int): Flow<NetworkResponse<List<Movie>>>
}

class PopularMoviesUseCaseImpl(
    private val repository: MovieRepository,
    private val mapper: DataToDomainMapper
) : PopularMoviesUseCase {
    override suspend fun getPopularMovies(page: Int): Flow<NetworkResponse<List<Movie>>> {
        return repository.getPopularMovies(page = page).map { response ->
            when (response) {
                is NetworkResponse.Success -> {
                    NetworkResponse.Success(
                        response.data.movieResults.map { movieResult ->
                            mapper.map(movieResult)
                        }
                    )
                }

                is NetworkResponse.Error -> {
                    NetworkResponse.Error(response.exception)
                }

                is NetworkResponse.Loading -> {
                    NetworkResponse.Loading
                }

                NetworkResponse.LoadingMore -> {
                    NetworkResponse.LoadingMore
                }
            }
        }
    }
}