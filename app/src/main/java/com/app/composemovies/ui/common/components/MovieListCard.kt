package com.app.composemovies.ui.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.composemovies.ui.common.model.MovieUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListCard(movieUiState: MovieUiState, onClick: (movieId: Int) -> Unit = {}) {
    Card(
        onClick = {
            onClick(movieUiState.id)

        }, modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(model = movieUiState.posterPath),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.35f)
                    .fillMaxHeight(1f),
                contentScale = Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = movieUiState.title,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = movieUiState.overview,
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movieUiState.voteAverage.toString(),
                    maxLines = 4,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary),
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieListCardPreview() {
    MovieListCard(
        movieUiState = MovieUiState(
            id = 1,
            title = "Title",
            posterPath = "https://image.tmdb.org/t/p/w500/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
            backdropPath = "https://image.tmdb.org/t/p/w1280/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
            overview = "Overview",
            voteAverage = 7.5,
            releaseDate = "2021-09-30"
        )
    )
}