package com.example.moviesappv2.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappv2.common.MoviesListType
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.domain.model.MoviesList
import com.example.moviesappv2.domain.use_case.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Omar Elashry on 7/8/2023.
 */
@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _moviesList = MutableLiveData<Resource<MoviesList>>()
    val moviesList: LiveData<Resource<MoviesList>> get() = _moviesList

    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        getMoviesListUseCase(MoviesListType.POPULAR)
            .onEach { _moviesList.value = it }
            .launchIn(viewModelScope)
    }


}