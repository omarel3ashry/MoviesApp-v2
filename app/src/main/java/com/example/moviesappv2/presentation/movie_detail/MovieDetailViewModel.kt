package com.example.moviesappv2.presentation.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappv2.common.MoviesListType
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.domain.model.Movie
import com.example.moviesappv2.domain.model.MoviesList
import com.example.moviesappv2.domain.use_case.GetMovieDetailUseCase
import com.example.moviesappv2.domain.use_case.GetSimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Omar Elashry on 7/13/2023.
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _movieDetail = MutableLiveData<Resource<Movie>>()
    private val _similarMovies = MutableLiveData<Resource<MoviesList>>()
    val movieDetail: LiveData<Resource<Movie>> get() = _movieDetail
    val similarMovies: LiveData<Resource<MoviesList>> get() = _similarMovies

    init {
        val movieId = savedStateHandle.get<Int>("movieId")
        getMovieDetail(movieId!!)
        getSimilarMovies(movieId)
    }

    private fun getMovieDetail(movieId: Int) {
        getMovieDetailUseCase(movieId)
            .onEach { _movieDetail.value = it }
            .launchIn(viewModelScope)
    }

    private fun getSimilarMovies(movieId: Int) {
        getSimilarMoviesUseCase(movieId)
            .onEach { _similarMovies.value = it }
            .launchIn(viewModelScope)
    }
}