package com.testapp.data

import com.testapp.data.api.FilmsApi
import com.testapp.data.database.dao.FilmDao
import com.testapp.data.database.entity.FilmEntity
import com.testapp.data.database.entity.toFilm
import com.testapp.data.repository.FilmRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.coroutines.CoroutineContext

class FilmRepositoryImplTest {

    private val filmApi: FilmsApi = mock()
    private val filmDao: FilmDao = mock()
    private val coroutineContext: CoroutineContext = Dispatchers.IO

    private val filmRepository = FilmRepositoryImpl(filmApi, filmDao, coroutineContext)

    private val films = listOf(
        FilmItem(1, "Film 1", "Description 1", "path 1"),
        FilmItem(2, "Film 2", "Description 2", "path 2"),
    )
    private val filmEntities = films.map(FilmItem::toFilmEntity)
    private val filmModels = filmEntities.map(FilmEntity::toFilm)

    @Test
    fun `subscribeToFullFilmList should return full film list`() = runTest {
        whenever(filmDao.getAllFilms()).thenReturn(flowOf(filmEntities))

        val actualResult = filmRepository.subscribeToFullFilmList().toList()

        assertThat(actualResult).containsExactly(filmModels)
        verify(filmDao).getAllFilms()
        verifyNoMoreInteractions(filmDao)
    }

    @Test
    fun `loadFilmList should return success result`() = runTest {
        whenever(filmApi.getFilms()).thenReturn(FilmResponse(1, films))

        val actualResult = filmRepository.loadFilmList()

        assertThat(actualResult.isSuccess).isTrue
        verify(filmApi).getFilms()
        verify(filmDao).insertFilms(filmEntities)
        verifyNoMoreInteractions(filmApi, filmDao)
    }


    @Test
    fun `loadFilmList should return failure result when database insert fails`() = runTest {
        val expectedException = RuntimeException("Database insert failed")
        whenever(filmApi.getFilms()).thenReturn(FilmResponse(1, films))
        whenever(filmDao.insertFilms(any())).thenThrow(expectedException)

        val actualResult = filmRepository.loadFilmList()

        assertThat(actualResult.isFailure).isTrue
        assertThrows(expectedException::class.java) {
            actualResult.getOrThrow()
        }
        verify(filmApi).getFilms()
        verify(filmDao).insertFilms(filmEntities)
        verifyNoMoreInteractions(filmApi, filmDao)
    }
}
