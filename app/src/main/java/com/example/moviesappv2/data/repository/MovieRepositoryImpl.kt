package com.example.moviesappv2.data.repository

import androidx.lifecycle.LiveData
import com.example.moviesappv2.common.MoviesListType
import com.example.moviesappv2.common.MoviesListType.NOW_PLAYING
import com.example.moviesappv2.common.MoviesListType.POPULAR
import com.example.moviesappv2.common.MoviesListType.TOP_RATED
import com.example.moviesappv2.common.MoviesListType.UPCOMING
import com.example.moviesappv2.data.local.MovieDao
import com.example.moviesappv2.data.local.entities.MovieEntity
import com.example.moviesappv2.data.remote.MoviesApi
import com.example.moviesappv2.data.remote.dto.MovieDetailDto
import com.example.moviesappv2.data.remote.dto.MoviesListDto
import com.example.moviesappv2.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Created by Omar Elashry on 7/8/2023.
 */
class MovieRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val movieDao: MovieDao
) : MovieRepository {

    // remote
    override suspend fun getMovieById(id: Int): MovieDetailDto {
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

    override suspend fun getSimilarMovies(id: Int): MoviesListDto {
        return moviesApi.getSimilarMovies(id)
    }

    // local
    override suspend fun getFavMovieById(id: Int): MovieEntity {
        return movieDao.getMovie(id)
    }

    override suspend fun getFavMovies(): LiveData<List<MovieEntity>> {
        return movieDao.getMovies()
    }

    override suspend fun addMovieToFav(movieEntity: MovieEntity) {
        return movieDao.insertMovie(movieEntity)
    }

    override suspend fun updateFavMovie(movieEntity: MovieEntity) {
        return movieDao.insertMovie(movieEntity)
    }

    override suspend fun removeFavMovie(movieEntity: MovieEntity) {
        return movieDao.deleteMovie(movieEntity)
    }
}