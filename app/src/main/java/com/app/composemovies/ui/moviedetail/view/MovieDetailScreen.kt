package com.app.composemovies.ui.moviedetail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.composemovies.ui.moviedetail.model.ActorUiState
import com.app.composemovies.ui.moviedetail.model.MovieDetailUiState
import com.app.composemovies.ui.moviedetail.viewmodel.MovieDetailViewModel

@Composable
fun MovieDetailScreen(movieId: Int, viewModel: MovieDetailViewModel) {
    LaunchedEffect(key1 = movieId) {
        viewModel.getMovieDetail(movieId)
    }

    val movieDetail = viewModel.movieDetailState.collectAsState().value

    if (movieDetail.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            MovieDetailHeader(movie = movieDetail)
            MovieDetailBody(movie = movieDetail)
        }
    }
}

@Composable
fun MovieDetailHeader(movie: MovieDetailUiState) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = movie.backdropPath,
            contentDescription = movie.title,
            contentScale = Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        )
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MovieDetailBody(movie: MovieDetailUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(PaddingValues(start = 8.dp, end = 8.dp))
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = CenterVertically) {
            AssistChip(
                label = { Text(text = movie.voteAverage.toString()) },
                leadingIcon = { Text(text = "⭐") },
                onClick = {},
                shape = MaterialTheme.shapes.large
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))
            AssistChip(
                label = { Text(text = movie.runtime.toString()) },
                leadingIcon = { Text(text = "⏰") },
                onClick = {},
                shape = MaterialTheme.shapes.large
            )
            Spacer(modifier = Modifier.padding(start = 8.dp))
            AssistChip(
                label = { Text(text = movie.releaseDate.toString()) },
                leadingIcon = { Text(text = "\uD83D\uDDD3") },
                onClick = {},
                shape = MaterialTheme.shapes.large
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        )

        Text(text = "Overview", style = MaterialTheme.typography.labelSmall)
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = PaddingValues(top = 8.dp, bottom = 8.dp))
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        )

        Text(text = "Genres", style = MaterialTheme.typography.labelSmall)

        FlowRow(modifier = Modifier.fillMaxWidth()) {
            movie.genres.forEach { genre ->
                AssistChip(
                    label = { Text(text = genre) },
                    onClick = {},
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
        )
        Text(text = "Actors", style = MaterialTheme.typography.labelSmall)
        LazyRow(
            Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movie.actors.count()) { actor ->
                ActorItem(actor = movie.actors[actor])
            }
        }

    }
}

@Composable
fun ActorItem(actor: ActorUiState) {
    Column(
        modifier = Modifier.width(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = actor.profilePath,
            contentDescription = actor.name,
            contentScale = Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Text(
            text = actor.name,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(top = 4.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
