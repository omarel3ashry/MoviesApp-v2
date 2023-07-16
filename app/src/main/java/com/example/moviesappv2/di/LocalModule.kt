package com.example.moviesappv2.di

import android.content.Context
import androidx.room.Room
import com.example.moviesappv2.data.local.MovieDao
import com.example.moviesappv2.data.local.MovieDatabase
import com.example.moviesappv2.data.local.entities.ListConverter
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Omar Elashry on 7/15/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideTypeConverter(gson: Gson): ListConverter = ListConverter(gson)

    @Singleton
    @Provides
    fun provideMovieDatabase(
        @ApplicationContext context: Context,
        listConverter: ListConverter
    ): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            MovieDatabase.DB_NAME
        )
            .addTypeConverter(listConverter)
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }
}