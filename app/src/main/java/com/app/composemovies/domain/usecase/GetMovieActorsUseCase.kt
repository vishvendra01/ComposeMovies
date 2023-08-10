package com.app.composemovies.domain.usecase

import com.app.composemovies.domain.mapper.DataToDomainMapper
import com.app.composemovies.domain.models.Cast
import com.app.composemovies.domain.repository.MovieRepository

interface GetMovieActorsUseCase {
    suspend fun getMovieActors(movieId: Int): List<Cast>
}

class GetMovieActorsUseCaseImpl(
    private val repository: MovieRepository,
    private val mapper: DataToDomainMapper
) : GetMovieActorsUseCase {

    override suspend fun getMovieActors(movieId: Int): List<Cast> {
        return repository.getActors(movieId).cast.map { mapper.map(it) }
    }

}