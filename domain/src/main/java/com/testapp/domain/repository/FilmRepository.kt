package com.testapp.domain.repository

import com.testapp.domain.entity.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    fun subscribeToFullFilmList(): Flow<List<Film>>

    suspend fun loadFilmList(): Result<Unit>
}