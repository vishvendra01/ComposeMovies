package com.app.composemovies.di

import com.app.composemovies.data.remote.MovieApiService
import com.app.composemovies.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideMovieApiService(): MovieApiService {
        return RetrofitClient
            .getRetrofitClient()
            .create(MovieApiService::class.java)
    }
}