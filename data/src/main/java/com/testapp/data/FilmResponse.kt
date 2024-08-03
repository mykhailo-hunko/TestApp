package com.testapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<FilmItem>,
)
