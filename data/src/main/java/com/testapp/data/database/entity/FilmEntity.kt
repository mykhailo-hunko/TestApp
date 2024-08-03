package com.testapp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testapp.domain.entity.Film

@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "poster_path") val posterPath: String,
)

internal fun FilmEntity.toFilm() = Film(
    id = uid,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
)
