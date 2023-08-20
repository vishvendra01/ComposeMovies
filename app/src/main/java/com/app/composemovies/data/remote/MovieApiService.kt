package com.app.composemovies.data.remote

import com.app.composemovies.BuildConfig
import com.app.composemovies.data.models.GenreResponse
import com.app.composemovies.data.models.MovieActorResponse
import com.app.composemovies.data.models.MovieDetailResponse
import com.app.composemovies.data.models.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/popular?api_key=${BuildConfig.TMBD_API_KEY}")
    suspend fun getPopularMovies(@Query("page") page: Int): PopularMovieResponse

    @GET("movie/now_playing?api_key=${BuildConfig.TMBD_API_KEY}")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): PopularMovieResponse

    @GET("discover/movie?api_key=${BuildConfig.TMBD_API_KEY}")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): PopularMovieResponse

    @GET("genre/movie/list?api_key=${BuildConfig.TMBD_API_KEY}")
    suspend fun getGenres(): GenreResponse

    @GET("movie/{movieId}?api_key=${BuildConfig.TMBD_API_KEY}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Int): MovieDetailResponse

    @GET("movie/{movieId}/credits?api_key=${BuildConfig.TMBD_API_KEY}")
    suspend fun getActors(@Path("movieId") movieId: Int): MovieActorResponse

}