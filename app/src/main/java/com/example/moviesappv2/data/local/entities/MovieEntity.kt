package com.example.moviesappv2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesappv2.common.Constants
import com.example.moviesappv2.common.MovieGenre
import com.example.moviesappv2.domain.model.Movie
import com.example.moviesappv2.domain.model.ProductionCompany

/**
 * Created by Omar Elashry on 7/15/2023.
 */
@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val genres: List<String>,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val imdbId: String?,
    val revenue: Int?,
    val tagline: String?
)

fun MovieEntity.toMovie(): Movie = Movie(
    id = id,
    adult = adult,
    backdropPath = backdropPath,
    genres = genres,
    originalLanguage = originalLanguage,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    voteAverage = voteAverage,
    imdbId = imdbId,
    revenue = revenue,
    tagline = tagline,
    productionCompanies = null
)
