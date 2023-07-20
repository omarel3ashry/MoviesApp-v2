package com.example.moviesappv2.data.remote

import com.example.moviesappv2.data.remote.dto.MovieCreditDto
import com.example.moviesappv2.data.remote.dto.MovieDetailDto
import com.example.moviesappv2.data.remote.dto.MoviesListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by Omar Elashry on 7/8/2023.
 */
interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesListDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MoviesListDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MoviesListDto

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(): MoviesListDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): MovieDetailDto

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int): MoviesListDto

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredit(@Path("movie_id") movieId: Int): MovieCreditDto

    @GET("discover/movie")
    suspend fun getFilteredMovies(@QueryMap queryMap: Map<String, String>): MoviesListDto
}