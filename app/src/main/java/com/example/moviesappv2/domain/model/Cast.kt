package com.example.moviesappv2.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Omar Elashry on 7/17/2023.
 */
data class Cast(
    val name: String,
    val character: String,
    val profilePath: String
)
