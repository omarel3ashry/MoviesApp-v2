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
import javax.inject.Inject

/**
 * Created by Omar Elashry on 7/8/2023.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _popularMoviesList = MutableLiveData<Resource<MoviesList>>()
    private val _nowPlayingMoviesList = MutableLiveData<Resource<MoviesList>>()
    private val _upcomingMoviesList = MutableLiveData<Resource<MoviesList>>()
    private val _topRatedMoviesList = MutableLiveData<Resource<MoviesList>>()
    private val _moviesListType = MutableLiveData<MoviesListType>()
    val popularMoviesList: LiveData<Resource<MoviesList>> get() = _popularMoviesList
    val nowPlayingMoviesList: LiveData<Resource<MoviesList>> get() = _nowPlayingMoviesList
    val upcomingMoviesList: LiveData<Resource<MoviesList>> get() = _upcomingMoviesList
    val topRatedMoviesList: LiveData<Resource<MoviesList>> get() = _topRatedMoviesList
    val moviesListType: LiveData<MoviesListType> get() = _moviesListType

    init {
        getPopularMoviesList()
        getNowPlayingMoviesList()
        getUpcomingMoviesList()
        getTopRatedMoviesList()
    }

    fun setMoviesListType(listType: MoviesListType) {
        _moviesListType.value = listType
    }

    private fun getPopularMoviesList() {
        getMoviesListUseCase(MoviesListType.POPULAR)
            .onEach { _popularMoviesList.value = it }
            .launchIn(viewModelScope)
    }

    private fun getNowPlayingMoviesList() {
        getMoviesListUseCase(MoviesListType.NOW_PLAYING)
            .onEach { _nowPlayingMoviesList.value = it }
            .launchIn(viewModelScope)
    }

    private fun getUpcomingMoviesList() {
        getMoviesListUseCase(MoviesListType.UPCOMING)
            .onEach { _upcomingMoviesList.value = it }
            .launchIn(viewModelScope)
    }

    private fun getTopRatedMoviesList() {
        getMoviesListUseCase(MoviesListType.TOP_RATED)
            .onEach { _topRatedMoviesList.value = it }
            .launchIn(viewModelScope)
    }


}