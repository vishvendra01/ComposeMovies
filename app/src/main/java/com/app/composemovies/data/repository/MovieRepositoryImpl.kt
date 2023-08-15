package com.app.composemovies.data.repository

import com.app.composemovies.data.mapper.DataToDomainMapper
import com.app.composemovies.data.remote.MovieApiService
import com.app.composemovies.domain.models.Cast
import com.app.composemovies.domain.models.Movie
import com.app.composemovies.domain.models.MovieDetail
import com.app.composemovies.domain.repository.MovieRepository
import com.app.composemovies.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val apiService: MovieApiService,
    private val dataToDomainMapper: DataToDomainMapper
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): Flow<NetworkResponse<List<Movie>>> {
        return flow {
            if (page == 1) {
                emit(NetworkResponse.Loading)
            } else {
                emit(NetworkResponse.LoadingMore)
            }
            val response = try {
                apiService.getPopularMovies(page)
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
                return@flow
            }
            emit(NetworkResponse.Success(response.movieResults.map { movieResult ->
                dataToDomainMapper.map(
                    movieResult
                )
            }))
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): Flow<NetworkResponse<List<Movie>>> {
        return flow {
            if (page == 1) {
                emit(NetworkResponse.Loading)
            } else {
                emit(NetworkResponse.LoadingMore)
            }
            val response = try {
                apiService.getNowPlayingMovies(page)
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
                return@flow
            }
            emit(NetworkResponse.Success(response.movieResults.map { movieResult ->
                dataToDomainMapper.map(
                    movieResult
                )
            }))
        }
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return dataToDomainMapper.map(apiService.getMovieDetail(movieId))
    }

    override suspend fun getActors(movieId: Int): List<Cast> {
        return apiService.getActors(movieId).cast.map { dataToDomainMapper.map(it) }
    }

}