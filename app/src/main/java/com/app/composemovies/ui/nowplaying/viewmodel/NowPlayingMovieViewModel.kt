package com.app.composemovies.ui.nowplaying.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composemovies.domain.models.Movie
import com.app.composemovies.domain.usecase.NowPlayingMoviesUseCase
import com.app.composemovies.ui.common.mapper.DomainToUiMapper
import com.app.composemovies.ui.nowplaying.model.NowPlayingMovieUiState
import com.app.composemovies.utils.NetworkResponse
import com.app.composemovies.utils.NetworkResponse.Error
import com.app.composemovies.utils.NetworkResponse.Loading
import com.app.composemovies.utils.NetworkResponse.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingMovieViewModel @Inject constructor(
    private val nowPlayingMoviesUseCase: NowPlayingMoviesUseCase,
    private val domainToUiMapper: DomainToUiMapper
) : ViewModel() {

    private var page = 1
    private var _moviesState: MutableStateFlow<NowPlayingMovieUiState> =
        MutableStateFlow(NowPlayingMovieUiState())
    val moviesState: StateFlow<NowPlayingMovieUiState> = _moviesState

    init {
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            nowPlayingMoviesUseCase.getPopularMovies(page).collect {
                handlePopularMovieResponse(it)
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            if (_moviesState.value.isLoadMore) return@launch

            page++
            nowPlayingMoviesUseCase.getPopularMovies(page).collect {
                handlePopularMovieResponse(it)
            }
        }
    }

    private fun handlePopularMovieResponse(movies: NetworkResponse<List<Movie>>) {
        when (movies) {
            is Success -> {
                _moviesState.value = NowPlayingMovieUiState(
                    isLoading = false,
                    movieUiStates = _moviesState.value.movieUiStates + movies.data.map {
                        domainToUiMapper.map(it)
                    })
            }

            is Error -> {
                _moviesState.value = NowPlayingMovieUiState(
                    isLoading = false,
                    error = movies.exception.message ?: "Something went wrong"
                )
            }

            is Loading -> {
                _moviesState.value = NowPlayingMovieUiState(
                    isLoading = true
                )
            }

            NetworkResponse.LoadingMore -> {
                _moviesState.value = _moviesState.value.copy(
                    isLoading = false,
                    isLoadMore = true
                )
            }
        }
    }
}