package com.app.composemovies.data.repository

import com.app.composemovies.data.models.MovieActorResponse
import com.app.composemovies.data.models.MovieDetailResponse
import com.app.composemovies.data.models.PopularMovieResponse
import com.app.composemovies.data.remote.MovieApiService
import com.app.composemovies.domain.repository.MovieRepository
import com.app.composemovies.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val apiService: MovieApiService
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): Flow<NetworkResponse<PopularMovieResponse>> {
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
            emit(NetworkResponse.Success(response))
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): Flow<NetworkResponse<PopularMovieResponse>> {
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
            emit(NetworkResponse.Success(response))
        }
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetailResponse {
        return apiService.getMovieDetail(movieId)
    }

    override suspend fun getActors(movieId: Int): MovieActorResponse {
        return apiService.getActors(movieId)
    }

}