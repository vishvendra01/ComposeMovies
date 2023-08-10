package com.app.composemovies.ui.nowplaying.model

import com.app.composemovies.ui.common.model.MovieUiState

data class NowPlayingMovieUiState(
    val isLoading: Boolean = false,
    val movieUiStates: List<MovieUiState> = emptyList(),
    val error: String = ""
)