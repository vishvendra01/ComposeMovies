package com.app.composemovies.ui.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.app.composemovies.ui.nowplaying.view.NowPlayingMovieScreen
import com.app.composemovies.ui.nowplaying.viewmodel.NowPlayingMovieViewModel
import com.app.composemovies.ui.popular.view.PopularMovieScreen
import com.app.composemovies.ui.popular.viewmodel.PopularMovieViewModel

@Composable
fun TabScreen(
    popularMovieViewModel: PopularMovieViewModel,
    nowPlayingMovieViewModel: NowPlayingMovieViewModel,
    navController: NavController
) {
    val popularMovieUiState = popularMovieViewModel.moviesState.collectAsState().value
    val nowPlayingMovieUiState = nowPlayingMovieViewModel.moviesState.collectAsState().value
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Popular", "Now Playing")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                )
            }
        }
        when (tabIndex) {
            0 -> PopularMovieScreen(movieUiState = popularMovieUiState, navController = navController) {
                popularMovieViewModel.loadMore()
            }

            1 -> NowPlayingMovieScreen(movieUiState = nowPlayingMovieUiState)
        }
    }
}