package com.example.moviesappv2.presentation.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.domain.model.Movie
import com.example.moviesappv2.domain.model.MoviesList
import com.example.moviesappv2.domain.use_case.AddMovieToFavUseCase
import com.example.moviesappv2.domain.use_case.GetMovieDetailUseCase
import com.example.moviesappv2.domain.use_case.GetSimilarMoviesUseCase
import com.example.moviesappv2.domain.use_case.RemoveFavMovieUseCase
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
    private val addMovieToFavUseCase: AddMovieToFavUseCase,
    private val removeFavMovieUseCase: RemoveFavMovieUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _movieDetail = MutableLiveData<Resource<Movie>>()
    private val _similarMovies = MutableLiveData<Resource<MoviesList>>()
    private val _favMovie = MutableLiveData<Movie>()
    private val _favMovieId = MutableLiveData<Resource<Long>>()
    val movieDetail: LiveData<Resource<Movie>> get() = _movieDetail
    val similarMovies: LiveData<Resource<MoviesList>> get() = _similarMovies
    val favMovie: LiveData<Movie> get() = _favMovie
    val favMovieId: LiveData<Resource<Long>> get() = _favMovieId

    init {
        val movieId = savedStateHandle.get<Int>("movieId")
        val mFavMovie = savedStateHandle.get<Movie>("movie")
        if (movieId != null) {
            getMovieDetail(movieId)
            getSimilarMovies(movieId)
        } else {
            getMovieDetail(mFavMovie!!)
        }
    }

    private fun getMovieDetail(movieId: Int) {
        getMovieDetailUseCase(movieId)
            .onEach { _movieDetail.value = it }
            .launchIn(viewModelScope)
    }

    private fun getMovieDetail(movie: Movie) {
        _favMovie.value = movie
    }

    private fun getSimilarMovies(movieId: Int) {
        getSimilarMoviesUseCase(movieId)
            .onEach { _similarMovies.value = it }
            .launchIn(viewModelScope)
    }

    fun addMovieToFav(movie: Movie) {
        addMovieToFavUseCase(movie).onEach { _favMovieId.value = it }
            .launchIn(viewModelScope)
    }

//    fun addMovieToFav(movie: Movie) {
//        viewModelScope.launch {
//            _favMovieId.value = addMovieToFavUseCase.invoke(movie)
//            Log.d("MovieDetailViewModel", "addMovieToFav: ${_favMovieId.value} ")
//        }
//    }

    fun removeMovieFromFav(movie: Movie) {
//        removeFavMovieUseCase(movie).onEach { _favMovie.value = it }
//            .launchIn(viewModelScope)
    }
}