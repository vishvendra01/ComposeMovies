package com.app.composemovies.ui.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.composemovies.ui.nowplaying.viewmodel.NowPlayingMovieViewModel
import com.app.composemovies.ui.popular.viewmodel.PopularMovieViewModel
import com.app.composemovies.ui.search.ui.SearchScreen
import com.app.composemovies.ui.search.viewmodel.SearchViewModel

@Composable
fun MainScreen(
    popularMovieViewModel: PopularMovieViewModel,
    nowPlayingMovieViewModel: NowPlayingMovieViewModel,
    searchViewModel: SearchViewModel,
    navController: NavController
) {
    var selectedItem by remember { mutableStateOf(0) }
    val icons = listOf(Icons.Filled.Movie, Icons.Filled.Search)
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when (selectedItem) {
                0 -> MoviesTabScreen(
                    popularMovieViewModel = popularMovieViewModel,
                    nowPlayingMovieViewModel = nowPlayingMovieViewModel,
                    navController = navController
                )

                1 -> SearchScreen(searchViewModel = searchViewModel, navController = navController)
            }
        }

        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            NavigationBarItem(
                icon = { Icon(icons[0], contentDescription = "Movies") },
                selected = selectedItem == 0,
                onClick = { selectedItem = 0 }
            )
            NavigationBarItem(
                icon = { Icon(icons[1], contentDescription = "Search") },
                selected = selectedItem == 1,
                onClick = { selectedItem = 1 }
            )
        }
    }
}