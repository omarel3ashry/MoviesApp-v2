package com.example.moviesappv2.data.remote

import com.example.moviesappv2.data.remote.dto.MovieCreditDto
import com.example.moviesappv2.data.remote.dto.MovieDetailDto
import com.example.moviesappv2.data.remote.dto.MoviesListDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Omar Elashry on 7/8/2023.
 */
interface MoviesApi {

    @GET("popular")
    suspend fun getPopularMovies(): MoviesListDto

    @GET("top_rated")
    suspend fun getTopRatedMovies(): MoviesListDto

    @GET("now_playing")
    suspend fun getNowPlayingMovies(): MoviesListDto

    @GET("upcoming")
    suspend fun getUpComingMovies(): MoviesListDto

    @GET("{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): MovieDetailDto

    @GET("{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int): MoviesListDto

    @GET("{movie_id}/credits")
    suspend fun getMovieCredit(@Path("movie_id") movieId: Int): MovieCreditDto
}