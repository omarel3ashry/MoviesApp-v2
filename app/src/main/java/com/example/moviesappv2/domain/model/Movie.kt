package com.example.moviesappv2.domain.model

/**
 * Created by Omar Elashry on 7/8/2023.
 */
data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<String>,
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)
