package com.testapp.data.repository

import com.testapp.data.FilmItem
import com.testapp.data.api.FilmsApi
import com.testapp.data.database.dao.FilmDao
import com.testapp.data.database.entity.FilmEntity
import com.testapp.data.database.entity.toFilm
import com.testapp.data.toFilmEntity
import com.testapp.domain.entity.Film
import com.testapp.domain.repository.FilmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FilmRepositoryImpl(
    private val filmApi: FilmsApi,
    private val filmDao: FilmDao,
    private val coroutineContext: CoroutineContext = Dispatchers.IO,
) : FilmRepository {
    override fun subscribeToFullFilmList(): Flow<List<Film>> =
        filmDao
            .getAllFilms()
            .map { films -> films.map(FilmEntity::toFilm) }

    override suspend fun loadFilmList(): Result<Unit> = runCatching {
        withContext(coroutineContext) {
            val films = filmApi.getFilms()
            filmDao.insertFilms(films.results.map(FilmItem::toFilmEntity))
        }
    }

}