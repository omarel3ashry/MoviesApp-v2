package com.example.moviesappv2.domain.model

/**
 * Created by Omar Elashry on 7/8/2023.
 */
data class MoviesList(
    val page: Int,
    val movies: List<Movie>,
    val totalPages: Int,
)
