package com.app.composemovies.ui.moviedetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composemovies.domain.usecase.GetMovieActorsUseCase
import com.app.composemovies.domain.usecase.MovieDetailUseCase
import com.app.composemovies.ui.common.mapper.DomainToUiMapper
import com.app.composemovies.ui.moviedetail.model.MovieDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val getMovieActorsUseCase: GetMovieActorsUseCase,
    private val domainToUiMapper: DomainToUiMapper
) : ViewModel() {
    private var _movieDetailState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(MovieDetailUiState.Initial)
    val movieDetailState: StateFlow<MovieDetailUiState> = _movieDetailState


    fun getMovieDetail(movieId: Int) {
        _movieDetailState.value = _movieDetailState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val movieDetail = movieDetailUseCase.getMovieDetail(movieId)
                val actors = getMovieActorsUseCase.getMovieActors(movieId)
                _movieDetailState.value =
                    domainToUiMapper.map(movieDetail, actors).copy(isLoading = false)
            } catch (e: Exception) {
                _movieDetailState.value = _movieDetailState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Something went wrong"
                )
            }
        }
    }

}