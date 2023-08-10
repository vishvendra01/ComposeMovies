package com.app.composemovies.di

import com.app.composemovies.domain.mapper.DataToDomainMapper
import com.app.composemovies.domain.repository.MovieRepository
import com.app.composemovies.domain.usecase.GetMovieActorsUseCase
import com.app.composemovies.domain.usecase.GetMovieActorsUseCaseImpl
import com.app.composemovies.domain.usecase.MovieDetailUseCase
import com.app.composemovies.domain.usecase.MovieDetailUseCaseImpl
import com.app.composemovies.domain.usecase.NowPlayingMoviesUseCase
import com.app.composemovies.domain.usecase.NowPlayingMoviesUseCaseImpl
import com.app.composemovies.domain.usecase.PopularMoviesUseCase
import com.app.composemovies.domain.usecase.PopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun providePopularMovieUseCase(
        repository: MovieRepository, mapper: DataToDomainMapper
    ): PopularMoviesUseCase {
        return PopularMoviesUseCaseImpl(repository, mapper)
    }

    @Provides
    fun provideNowPlayingMovieUseCase(
        repository: MovieRepository, mapper: DataToDomainMapper
    ): NowPlayingMoviesUseCase {
        return NowPlayingMoviesUseCaseImpl(repository, mapper)
    }

    @Provides
    fun provideMovieDetailUseCase(
        repository: MovieRepository, mapper: DataToDomainMapper
    ): MovieDetailUseCase {
        return MovieDetailUseCaseImpl(repository, mapper)
    }

    @Provides
    fun provideMovieActorsUseCase(
        repository: MovieRepository, mapper: DataToDomainMapper
    ): GetMovieActorsUseCase {
        return GetMovieActorsUseCaseImpl(repository, mapper)
    }
}