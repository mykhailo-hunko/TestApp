package com.testapp.data

import com.testapp.data.database.entity.FilmEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmItem(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String,
)

internal fun FilmItem.toFilmEntity() = FilmEntity(
    uid = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
)