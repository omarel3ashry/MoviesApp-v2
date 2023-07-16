package com.example.moviesappv2.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappv2.data.local.entities.MovieEntity
import com.example.moviesappv2.domain.model.Movie
import com.example.moviesappv2.domain.use_case.GetFavMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Omar Elashry on 7/15/2023.
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavMoviesUseCase: GetFavMoviesUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _favoriteMovies = MutableLiveData<List<Movie>>()
    val favoriteMovies: LiveData<List<Movie>> get() = _favoriteMovies

    init {
        getFavMovies()
    }

//    private fun getFavMovies() {
//        viewModelScope.launch { _favoriteMovies.value = getFavMoviesUseCase.invoke() }
//    }

    private fun getFavMovies() {
        getFavMoviesUseCase().onEach { _favoriteMovies.value = it }.launchIn(viewModelScope)
    }
}