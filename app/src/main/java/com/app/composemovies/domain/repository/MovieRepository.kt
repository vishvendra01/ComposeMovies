package com.app.composemovies.domain.repository

import com.app.composemovies.data.models.MovieActorResponse
import com.app.composemovies.data.models.MovieDetailResponse
import com.app.composemovies.data.models.PopularMovieResponse
import com.app.composemovies.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(page: Int): Flow<NetworkResponse<PopularMovieResponse>>

    suspend fun getNowPlayingMovies(): Flow<NetworkResponse<PopularMovieResponse>>

    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse

    suspend fun getActors(movieId: Int): MovieActorResponse
}