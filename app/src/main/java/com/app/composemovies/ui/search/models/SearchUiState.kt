package com.app.composemovies.ui.search.models

import com.app.composemovies.ui.common.model.MovieUiState

data class SearchUiState(
    val genres: List<GenreUiState> = emptyList(),
    val results : List<MovieUiState> = emptyList(),
    val isLoading: Boolean = false,
    val resultShown: Boolean = false,
    val error: String? = null
)