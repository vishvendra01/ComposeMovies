package com.app.composemovies.ui.search.models

data class GenreUiState(
    val id: Int,
    val name: String,
    val isSelected: Boolean = false
)