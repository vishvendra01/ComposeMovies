package com.app.composemovies.ui.popular.model

import com.app.composemovies.ui.common.model.MovieUiState

data class PopularMovieUiState(
    val isLoading: Boolean = false,
    val isLoadMore: Boolean = false,
    val movieUiStates: List<MovieUiState> = emptyList(),
    val error: String = ""
)