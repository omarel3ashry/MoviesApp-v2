package com.example.moviesappv2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesappv2.data.local.entities.MovieEntity

/**
 * Created by Omar Elashry on 7/15/2023.
 */
@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DB_NAME = "movie_data_db"
    }
}