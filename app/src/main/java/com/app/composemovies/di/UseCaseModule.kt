package com.app.composemovies.di

import com.app.composemovies.domain.repository.MovieRepository
import com.app.composemovies.domain.usecase.GetGenresUseCase
import com.app.composemovies.domain.usecase.GetGenresUseCaseImpl
import com.app.composemovies.domain.usecase.GetMovieActorsUseCase
import com.app.composemovies.domain.usecase.GetMovieActorsUseCaseImpl
import com.app.composemovies.domain.usecase.GetMoviesByGenresUseCase
import com.app.composemovies.domain.usecase.GetMoviesByGenresUseCaseImpl
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
        repository: MovieRepository
    ): PopularMoviesUseCase {
        return PopularMoviesUseCaseImpl(repository)
    }

    @Provides
    fun provideNowPlayingMovieUseCase(
        repository: MovieRepository
    ): NowPlayingMoviesUseCase {
        return NowPlayingMoviesUseCaseImpl(repository)
    }

    @Provides
    fun provideMovieDetailUseCase(
        repository: MovieRepository
    ): MovieDetailUseCase {
        return MovieDetailUseCaseImpl(repository)
    }

    @Provides
    fun provideMovieActorsUseCase(
        repository: MovieRepository
    ): GetMovieActorsUseCase {
        return GetMovieActorsUseCaseImpl(repository)
    }

    @Provides
    fun provideGetGenresUseCase(
        repository: MovieRepository
    ): GetGenresUseCase {
        return GetGenresUseCaseImpl(repository)
    }

    @Provides
    fun provideGetMoviesByGenresUseCase(
        repository: MovieRepository
    ): GetMoviesByGenresUseCase {
        return GetMoviesByGenresUseCaseImpl(repository)
    }
}