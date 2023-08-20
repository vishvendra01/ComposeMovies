package com.app.composemovies.ui.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.composemovies.domain.usecase.GetGenresUseCase
import com.app.composemovies.domain.usecase.GetMoviesByGenresUseCase
import com.app.composemovies.ui.common.model.MovieUiState
import com.app.composemovies.ui.search.models.GenreUiState
import com.app.composemovies.ui.search.models.SearchResultsUiState
import com.app.composemovies.ui.search.models.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getMoviesByGenresUseCase: GetMoviesByGenresUseCase
) : ViewModel() {

    private var _searchState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState())
    val searchState: StateFlow<SearchUiState> = _searchState

    private var _searchResultsState: MutableStateFlow<SearchResultsUiState> =
        MutableStateFlow(SearchResultsUiState())
    val searchResultsState: StateFlow<SearchResultsUiState> = _searchResultsState

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            val genres = getGenresUseCase.getGenres()
            _searchState.value = _searchState.value.copy(
                genres = genres.map { genre ->
                    GenreUiState(
                        id = genre.id, name = genre.name
                    )
                }
            )
        }
    }

    fun getMoviesByGenre(genreId: Int) {
        viewModelScope.launch {
            _searchResultsState.value = _searchResultsState.value.copy(
                isLoading = true
            )
            val movies = getMoviesByGenresUseCase.getMoviesByGenres(genreId, 1)
            _searchResultsState.value = _searchResultsState.value.copy(
                isLoading = false,
                movies = movies.map { movie ->
                    MovieUiState(
                        id = movie.id,
                        title = movie.title,
                        posterPath = movie.posterPath,
                        backdropPath = movie.backdropPath,
                        overview = movie.overview,
                        voteAverage = movie.voteAverage,
                        releaseDate = movie.releaseDate
                    )
                }
            )
        }
    }
}