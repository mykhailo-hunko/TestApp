package com.testapp.data

import com.testapp.data.database.entity.FilmEntity
import com.testapp.domain.entity.Film
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmItem(
    @SerialName("id") val id: Int,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String,
)

internal fun FilmItem.toFilmEntity() = FilmEntity(
    uid = id,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
)