package com.app.composemovies.di

import com.app.composemovies.data.mapper.DataToDomainMapper
import com.app.composemovies.data.remote.MovieApiService
import com.app.composemovies.data.repository.MovieRepositoryImpl
import com.app.composemovies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providePopularMovieRepository(
        apiService: MovieApiService,
        dataToDomainMapper: DataToDomainMapper
    ): MovieRepository {
        return MovieRepositoryImpl(apiService, dataToDomainMapper)
    }
}