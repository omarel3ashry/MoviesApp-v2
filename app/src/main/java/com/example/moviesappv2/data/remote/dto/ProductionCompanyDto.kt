package com.example.moviesappv2.data.remote.dto

import com.example.moviesappv2.common.Constants
import com.example.moviesappv2.domain.model.ProductionCompany
import com.google.gson.annotations.SerializedName

/**
 * Created by Omar Elashry on 7/13/2023.
 */
data class ProductionCompanyDto(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)

fun ProductionCompanyDto.toProdCompany(): ProductionCompany = ProductionCompany(
    logoPath = Constants.BASE_MOVIE_IMAGE_URL + logoPath,
    name = name,
    originCountry = originCountry
)
