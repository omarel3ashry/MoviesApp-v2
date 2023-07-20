package com.example.moviesappv2.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.domain.model.MoviesFilter
import com.example.moviesappv2.domain.model.MoviesList
import com.example.moviesappv2.domain.use_case.GetFilteredMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Omar Elashry on 7/20/2023.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getFilteredMoviesUseCase: GetFilteredMoviesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _moviesFilter = MutableLiveData<MoviesFilter>()
    private val _filteredMovies = MutableLiveData<Resource<MoviesList>>()
    val moviesFilter: LiveData<MoviesFilter> get() = _moviesFilter
    val filteredMovies: LiveData<Resource<MoviesList>> get() = _filteredMovies

    fun setFilter(filter: MoviesFilter) {
        _moviesFilter.value = filter
    }

    fun searchMovies() {
        if (moviesFilter.value != null)
            getFilteredMoviesUseCase.invoke(moviesFilter.value!!)
                .onEach { _filteredMovies.value = it }
                .launchIn(viewModelScope)

    }

}