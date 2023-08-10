package com.app.composemovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.composemovies.ui.moviedetail.view.MovieDetailScreen
import com.app.composemovies.ui.moviedetail.viewmodel.MovieDetailViewModel
import com.app.composemovies.ui.nowplaying.viewmodel.NowPlayingMovieViewModel
import com.app.composemovies.ui.popular.viewmodel.PopularMovieViewModel
import com.app.composemovies.ui.tabs.TabScreen
import com.app.composemovies.ui.theme.ComposeMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val popularViewModel: PopularMovieViewModel by viewModels()
    private val nowPlayingViewModel: NowPlayingMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesApp(
                popularMovieViewModel = popularViewModel,
                nowPlayingMovieViewModel = nowPlayingViewModel
            )
        }
    }
}

@Composable
fun MoviesApp(
    popularMovieViewModel: PopularMovieViewModel,
    nowPlayingMovieViewModel: NowPlayingMovieViewModel
) {
    val navController = rememberNavController()
    ComposeMoviesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(navController = navController, startDestination = "movies") {
                composable("movies") {
                    TabScreen(
                        popularMovieViewModel = popularMovieViewModel,
                        nowPlayingMovieViewModel = nowPlayingMovieViewModel,
                        navController = navController
                    )
                }
                composable(
                    "movie_detail/{movieId}",
                    arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val viewModel: MovieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
                    val movieId = backStackEntry.arguments?.getInt("movieId")
                    MovieDetailScreen(movieId = movieId!!, viewModel)
                }
            }
        }
    }
}