package com.example.moviesappv2.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesappv2.data.local.entities.MovieEntity

/**
 * Created by Omar Elashry on 7/15/2023.
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)

    @Query("select * from movie_table")
    suspend fun getMovies(): LiveData<List<MovieEntity>>

    @Query("select * from movie_table where id = :id")
    suspend fun getMovie(id: Int): MovieEntity
}