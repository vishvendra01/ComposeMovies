package com.app.composemovies.ui.search.models

import com.app.composemovies.ui.common.model.MovieUiState

data class SearchResultsUiState(
    val isLoading: Boolean = false,
    val isLoadMore: Boolean = false,
    val movies: List<MovieUiState> = emptyList(),
    val error: String = ""
)