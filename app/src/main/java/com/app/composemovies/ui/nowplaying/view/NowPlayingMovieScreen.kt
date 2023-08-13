package com.app.composemovies.ui.nowplaying.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.composemovies.ui.common.components.ErrorView
import com.app.composemovies.ui.common.components.LoadingView
import com.app.composemovies.ui.common.components.MovieListCard
import com.app.composemovies.ui.nowplaying.model.NowPlayingMovieUiState

@Composable
fun NowPlayingMovieScreen(
    movieUiState: NowPlayingMovieUiState,
    navController: NavController,
    loadMore: () -> Unit
) {
    when {
        movieUiState.isLoading -> {
            LoadingView()
        }

        movieUiState.error.isNotEmpty() -> {
            ErrorView()
        }

        else -> {
            NowPlayingMovieList(
                movieUiState = movieUiState,
                loadMore = loadMore
            ) { movieId: Int ->
                navController.navigate("movie_detail/$movieId")
            }
        }
    }
}

@Composable
fun NowPlayingMovieList(
    movieUiState: NowPlayingMovieUiState,
    loadMore: () -> Unit = {},
    onCardClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        val movies = movieUiState.movieUiStates
        items(movies.count()) { index ->
            MovieListCard(movieUiState = movies[index], onClick = onCardClick)

            if (index == movies.count() - 1) {
                loadMore()
            }
        }

        if (movieUiState.isLoadMore) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}