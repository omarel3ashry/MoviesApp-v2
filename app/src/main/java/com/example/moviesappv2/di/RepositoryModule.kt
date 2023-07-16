package com.example.moviesappv2.di

import com.example.moviesappv2.data.local.MovieDao
import com.example.moviesappv2.data.remote.MoviesApi
import com.example.moviesappv2.data.repository.MovieRepositoryImpl
import com.example.moviesappv2.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Omar Elashry on 7/8/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        moviesApi: MoviesApi,
        movieDao: MovieDao
    ):
            MovieRepository {
        return MovieRepositoryImpl(moviesApi, movieDao)
    }

}