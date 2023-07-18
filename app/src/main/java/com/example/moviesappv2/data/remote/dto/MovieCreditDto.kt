package com.example.moviesappv2.data.remote.dto


data class MovieCreditDto(
    val id: Int,
    var cast: List<CastDto>
)