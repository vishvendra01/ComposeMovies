package com.app.composemovies.ui.nowplaying.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.composemovies.ui.common.components.ErrorView
import com.app.composemovies.ui.common.components.LoadingView
import com.app.composemovies.ui.common.components.MovieListCard
import com.app.composemovies.ui.common.model.MovieUiState
import com.app.composemovies.ui.nowplaying.model.NowPlayingMovieUiState

@Composable
fun NowPlayingMovieScreen(movieUiState: NowPlayingMovieUiState) {
    when {
        movieUiState.isLoading -> {
            LoadingView()
        }

        movieUiState.error.isNotEmpty() -> {
            ErrorView()
        }

        else -> {
            NowPlayingMovieList(movieUiState = movieUiState.movieUiStates) {

            }
        }
    }
}

@Composable
fun NowPlayingMovieList(movieUiState: List<MovieUiState>, onCardClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(movieUiState) { movieUiState ->
            MovieListCard(movieUiState = movieUiState, onClick = onCardClick)
        }
    }
}