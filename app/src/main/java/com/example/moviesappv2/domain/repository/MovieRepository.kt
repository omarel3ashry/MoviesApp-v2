package com.example.moviesappv2.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviesappv2.common.MoviesListType
import com.example.moviesappv2.data.local.entities.MovieEntity
import com.example.moviesappv2.data.remote.dto.MovieDetailDto
import com.example.moviesappv2.data.remote.dto.MovieDto
import com.example.moviesappv2.data.remote.dto.MoviesListDto

/**
 * Created by Omar Elashry on 7/8/2023.
 */
interface MovieRepository {

    // remote
    suspend fun getMovieById(id: Int): MovieDetailDto
    suspend fun getMoviesList(listType: MoviesListType): MoviesListDto
    suspend fun getSimilarMovies(id: Int): MoviesListDto

    // local
    suspend fun getFavMovieById(id: Int): MovieEntity
    suspend fun getFavMovies(): LiveData<List<MovieEntity>>
    suspend fun addMovieToFav(movieEntity: MovieEntity)
    suspend fun updateFavMovie(movieEntity: MovieEntity)
    suspend fun removeFavMovie(movieEntity: MovieEntity)
}