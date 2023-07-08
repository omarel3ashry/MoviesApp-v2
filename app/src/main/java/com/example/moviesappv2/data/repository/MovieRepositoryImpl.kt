package com.example.moviesappv2.data.repository

import com.example.moviesappv2.common.MoviesListType
import com.example.moviesappv2.common.MoviesListType.*
import com.example.moviesappv2.data.remote.MoviesApi
import com.example.moviesappv2.data.remote.dto.MovieDto
import com.example.moviesappv2.data.remote.dto.MoviesListDto
import com.example.moviesappv2.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Created by Omar Elashry on 7/8/2023.
 */
class MovieRepositoryImpl @Inject constructor(private val moviesApi: MoviesApi) : MovieRepository {

    override suspend fun getMovieById(id: Int): MovieDto {
        return moviesApi.getMovieDetail(id)
    }

    override suspend fun getMoviesList(listType: MoviesListType): MoviesListDto {
        return when (listType) {
            NOW_PLAYING -> moviesApi.getNowPlayingMovies()
            POPULAR -> moviesApi.getPopularMovies()
            TOP_RATED -> moviesApi.getTopRatedMovies()
            UPCOMING -> moviesApi.getUpComingMovies()
        }
    }
}