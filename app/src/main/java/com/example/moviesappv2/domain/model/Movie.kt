package com.example.moviesappv2.domain.model

import com.example.moviesappv2.data.local.entities.MovieEntity

/**
 * Created by Omar Elashry on 7/8/2023.
 */
data class Movie(
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
    val tagline: String?,
    val productionCompanies: List<ProductionCompany>?
)

fun Movie.toMovieEntity(): MovieEntity = MovieEntity(
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
    tagline = tagline
)