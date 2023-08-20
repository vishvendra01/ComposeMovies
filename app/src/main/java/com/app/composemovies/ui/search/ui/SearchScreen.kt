package com.app.composemovies.ui.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.composemovies.ui.search.models.GenreUiState
import com.app.composemovies.ui.search.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(searchViewModel: SearchViewModel, navController: NavController) {
    val searchUiState = searchViewModel.searchState.collectAsState().value

    var searchQuery by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text(text = "Search") },
            leadingIcon = { Text(text = "🔍") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )

        GenresSection(
            modifier = Modifier,
            genres = searchUiState.genres
        ) { id, name ->
            navController.navigate("search_results/$id/$name")
        }
    }
}

@Composable
fun GenresSection(
    modifier: Modifier = Modifier,
    genres: List<GenreUiState>,
    onClick: (id: Int, name: String) -> Unit
) {
    Box(modifier = modifier) {
        GenreChipList(genres = genres, onClick = onClick)
    }
}

@Composable
fun GenreChipList(genres: List<GenreUiState>, onClick: (id: Int, name: String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genres.count()) { index ->
            GenreChip(genre = genres[index], onClick = onClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreChip(genre: GenreUiState, onClick: (id: Int, name: String) -> Unit) {
    AssistChip(
        onClick = { onClick(genre.id, genre.name) },
        label = {
            Text(
                text = genre.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = MaterialTheme.shapes.small,
    )
}
