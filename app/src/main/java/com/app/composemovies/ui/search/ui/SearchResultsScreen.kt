package com.app.composemovies.ui.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.TopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.composemovies.ui.common.components.ErrorView
import com.app.composemovies.ui.common.components.LoadingView
import com.app.composemovies.ui.common.components.MovieListCard
import com.app.composemovies.ui.search.models.SearchResultsUiState
import com.app.composemovies.ui.search.viewmodel.SearchViewModel

@Composable
fun SearchResultScreen(
    searchViewModel: SearchViewModel,
    genreId: Int,
    genreName: String,
    navController: NavController,
    loadMore: () -> Unit = {}
) {
    val movieUiState = searchViewModel.searchResultsState.collectAsState().value

    LaunchedEffect(key1 = genreId) {
        searchViewModel.getMoviesByGenre(genreId)
    }

    when {
        movieUiState.isLoading -> {
            LoadingView()
        }

        movieUiState.error.isNotEmpty() -> {
            ErrorView()
        }

        else -> {
            SearchResultsMovieList(
                searchResultsUiState = movieUiState,
                genreName = genreName,
                loadMore = loadMore,
                onCardClick = { movieId: Int ->
                    navController.navigate("movie_detail/$movieId")
                })
        }
    }
}

@Composable
fun SearchResultsMovieList(
    searchResultsUiState: SearchResultsUiState,
    genreName: String,
    loadMore: () -> Unit = {},
    onCardClick: (Int) -> Unit
) {
    val movies = searchResultsUiState.movies
    val scrollState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = {
            Row {
                Text(text = genreName)
            }
        }, modifier = Modifier.fillMaxWidth())

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

            if (searchResultsUiState.isLoadMore) {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}