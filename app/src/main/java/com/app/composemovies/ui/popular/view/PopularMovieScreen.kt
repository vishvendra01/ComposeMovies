package com.app.composemovies.ui.popular.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.composemovies.ui.common.components.ErrorView
import com.app.composemovies.ui.common.components.LoadingView
import com.app.composemovies.ui.common.components.MovieListCard
import com.app.composemovies.ui.popular.model.PopularMovieUiState

@Composable
fun PopularMovieScreen(
    movieUiState: PopularMovieUiState,
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
            PopularMovieList(
                popularMovieUiState = movieUiState,
                loadMore = loadMore,
                onCardClick = { movieId: Int ->
                    navController.navigate("movie_detail/$movieId")
                })
        }
    }
}

@Composable
fun PopularMovieList(
    popularMovieUiState: PopularMovieUiState,
    loadMore: () -> Unit = {},
    onCardClick: (Int) -> Unit
) {
    val movies = popularMovieUiState.movieUiStates
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        state = scrollState
    ) {
        items(movies.count()) { index ->
            MovieListCard(movieUiState = movies[index]) { movieId: Int ->
                onCardClick(movieId)
            }

            if (index == movies.count() - 1) {
                loadMore()
            }
        }

        if (popularMovieUiState.isLoadMore) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}