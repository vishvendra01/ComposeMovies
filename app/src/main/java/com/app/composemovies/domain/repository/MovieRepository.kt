package com.app.composemovies.domain.repository

import com.app.composemovies.domain.models.Cast
import com.app.composemovies.domain.models.Movie
import com.app.composemovies.domain.models.MovieDetail
import com.app.composemovies.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(page: Int): Flow<NetworkResponse<List<Movie>>>

    suspend fun getNowPlayingMovies(page: Int): Flow<NetworkResponse<List<Movie>>>

    suspend fun getMovieDetail(movieId: Int): MovieDetail

    suspend fun getActors(movieId: Int): List<Cast>
}