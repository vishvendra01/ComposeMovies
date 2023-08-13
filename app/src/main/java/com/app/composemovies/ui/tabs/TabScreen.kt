package com.app.composemovies.ui.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.app.composemovies.ui.nowplaying.view.NowPlayingMovieScreen
import com.app.composemovies.ui.nowplaying.viewmodel.NowPlayingMovieViewModel
import com.app.composemovies.ui.popular.view.PopularMovieScreen
import com.app.composemovies.ui.popular.viewmodel.PopularMovieViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(
    popularMovieViewModel: PopularMovieViewModel,
    nowPlayingMovieViewModel: NowPlayingMovieViewModel,
    navController: NavController
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val popularMovieUiState = popularMovieViewModel.moviesState.collectAsState().value
    val nowPlayingMovieUiState = nowPlayingMovieViewModel.moviesState.collectAsState().value

    val tabs = listOf("Popular", "Now Playing")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
        HorizontalPager(pageCount = tabs.size, state = pagerState) { page ->
            when (page) {
                0 -> PopularMovieScreen(
                    movieUiState = popularMovieUiState,
                    navController = navController
                ) {
                    popularMovieViewModel.loadMore()
                }

                1 -> NowPlayingMovieScreen(
                    movieUiState = nowPlayingMovieUiState,
                    navController = navController
                ) {
                    nowPlayingMovieViewModel.loadMore()
                }

                else -> throw IllegalStateException("Tab index is not valid")
            }
        }
    }
}