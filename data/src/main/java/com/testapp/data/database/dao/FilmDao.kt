package com.testapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.testapp.data.database.entity.FilmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Insert
    fun insertFilms(films: List<FilmEntity>)

    @Query("SELECT * FROM films")
    fun getAllFilms(): Flow<List<FilmEntity>>
}