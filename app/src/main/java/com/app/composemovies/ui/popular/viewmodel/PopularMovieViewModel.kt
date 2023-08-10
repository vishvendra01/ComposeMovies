package com.app.composemovies.ui.popular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composemovies.domain.models.Movie
import com.app.composemovies.domain.usecase.PopularMoviesUseCase
import com.app.composemovies.ui.common.mapper.DomainToUiMapper
import com.app.composemovies.ui.popular.model.PopularMovieUiState
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
class PopularMovieViewModel @Inject constructor(
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val domainToUiMapper: DomainToUiMapper
) : ViewModel() {

    private var page = 1
    private var _moviesState: MutableStateFlow<PopularMovieUiState> =
        MutableStateFlow(PopularMovieUiState())
    val moviesState: StateFlow<PopularMovieUiState> = _moviesState

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            popularMoviesUseCase.getPopularMovies(page).collect {
                handlePopularMovieResponse(it)
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            if (_moviesState.value.isLoadMore) return@launch

            page++
            popularMoviesUseCase.getPopularMovies(page).collect {
                handlePopularMovieResponse(it)
            }
        }
    }

    private fun handlePopularMovieResponse(movies: NetworkResponse<List<Movie>>) {
        when (movies) {
            is Success -> {
                _moviesState.value = _moviesState.value.copy(
                    isLoading = false,
                    isLoadMore = false,
                    movieUiStates = _moviesState.value.movieUiStates + movies.data.map {
                        domainToUiMapper.map(it)
                    })
            }

            is Error -> {
                _moviesState.value = PopularMovieUiState(
                    isLoading = false,
                    error = movies.exception.message ?: "Something went wrong"
                )
            }

            is Loading -> {
                _moviesState.value = PopularMovieUiState(
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