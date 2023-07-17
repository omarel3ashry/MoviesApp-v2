package com.example.moviesappv2.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Omar Elashry on 7/13/2023.
 */
@Parcelize
data class ProductionCompany(
    val logoPath: String,
    val name: String
) : Parcelable
