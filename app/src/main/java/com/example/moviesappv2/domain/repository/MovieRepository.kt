package com.example.moviesappv2.domain.repository

import com.example.moviesappv2.common.MoviesListType
import com.example.moviesappv2.data.remote.dto.MovieDetailDto
import com.example.moviesappv2.data.remote.dto.MovieDto
import com.example.moviesappv2.data.remote.dto.MoviesListDto

/**
 * Created by Omar Elashry on 7/8/2023.
 */
interface MovieRepository {

    suspend fun getMovieById(id: Int): MovieDetailDto
    suspend fun getMoviesList(listType: MoviesListType): MoviesListDto
    suspend fun getSimilarMovies(id: Int): MoviesListDto
}