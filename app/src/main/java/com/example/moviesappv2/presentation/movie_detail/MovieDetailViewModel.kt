package com.example.moviesappv2.presentation.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesappv2.common.Resource
import com.example.moviesappv2.domain.model.Cast
import com.example.moviesappv2.domain.model.Movie
import com.example.moviesappv2.domain.model.MoviesList
import com.example.moviesappv2.domain.use_case.AddMovieToFavUseCase
import com.example.moviesappv2.domain.use_case.GetFavMovieUseCase
import com.example.moviesappv2.domain.use_case.GetMovieCastUseCase
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
    private val getFavMovieUseCase: GetFavMovieUseCase,
    private val getMovieCastUseCase: GetMovieCastUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _movieDetail = MutableLiveData<Resource<Movie>>()
    private val _movieCast = MutableLiveData<Resource<List<Cast>>>()
    private val _similarMovies = MutableLiveData<Resource<MoviesList>>()
    private val _favMovie = MutableLiveData<Movie>()
    private val _isFav = MutableLiveData<Boolean>()
    val movieDetail: LiveData<Resource<Movie>> get() = _movieDetail
    val movieCast: LiveData<Resource<List<Cast>>> get() = _movieCast
    val similarMovies: LiveData<Resource<MoviesList>> get() = _similarMovies
    val favMovie: LiveData<Movie> get() = _favMovie
    val isFav: LiveData<Boolean> get() = _isFav

    init {
        val movieId = savedStateHandle.get<Int>("movieId")
        val mFavMovie = savedStateHandle.get<Movie>("movie")
        if (movieId != null) {
            getMovieDetail(movieId)
            checkIfFav(movieId)
            getSimilarMovies(movieId)
            getMovieCast(movieId)
        } else {
            _isFav.value = true
            getMovieDetail(mFavMovie!!)
            getSimilarMovies(mFavMovie.id)
            getMovieCast(mFavMovie.id)
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

    private fun getMovieCast(movieId: Int) {
        getMovieCastUseCase(movieId)
            .onEach { _movieCast.value = it }
            .launchIn(viewModelScope)
    }


    fun addMovieToFav(movie: Movie) {
        addMovieToFavUseCase(movie).onEach {
            _isFav.value = it.data!!
        }
            .launchIn(viewModelScope)
    }

    fun removeMovieFromFav(movie: Movie) {
        removeFavMovieUseCase(movie).onEach {
            _isFav.value = !it.data!!
        }
            .launchIn(viewModelScope)
    }

    private fun checkIfFav(movieId: Int) {
        getFavMovieUseCase(movieId).onEach { resource ->
            _isFav.value = resource.status == Resource.Status.SUCCESS && resource.data != null
        }
            .launchIn(viewModelScope)
    }
}