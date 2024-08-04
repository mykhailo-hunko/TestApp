package com.testapp.domain

import com.testapp.domain.entity.Film
import com.testapp.domain.repository.FilmRepository
import com.testapp.domain.usecase.SubscribeToFilmListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.time.Duration.Companion.seconds

private val DEFAULT_LIST = listOf(
    Film(1, "Film 1", "Description 1", "123"),
    Film(2, "Film 2", "Description 2", "123"),
)


class SubscribeToFilmListUseCaseTest {

    @Test
    fun `should return full film list`() = runTest {
        val filmRepository: FilmRepository = mock {
            on { subscribeToFullFilmList() } doReturn flowOf(DEFAULT_LIST)
        }
        val useCase = SubscribeToFilmListUseCase(filmRepository)


        val actualResult = useCase.loadFullFilmList()

        actualResult.collect { films ->
            assertThat(films).isEqualTo(DEFAULT_LIST)
        }
        verify(filmRepository).subscribeToFullFilmList()
        verifyNoMoreInteractions(filmRepository)
    }

    @Test
    fun `should return empty list when no films available`() = runTest {
        val expectedFilmList = emptyList<Film>()
        val filmRepository: FilmRepository = mock {
            on { subscribeToFullFilmList() } doReturn flowOf(expectedFilmList)
        }
        val useCase = SubscribeToFilmListUseCase(filmRepository)

        val actualResult = useCase.loadFullFilmList().toList()

        assertThat(actualResult).containsExactly(expectedFilmList)
        verify(filmRepository).subscribeToFullFilmList()
        verifyNoMoreInteractions(filmRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return a few list from flow`() = runTest {
        val flow = flow {
            emit(DEFAULT_LIST)
            delay(1.seconds)
            emit(emptyList())
            delay(1.seconds)
            emit(DEFAULT_LIST)
        }
        val filmRepository: FilmRepository = mock {
            on { subscribeToFullFilmList() } doReturn flow
        }
        val useCase = SubscribeToFilmListUseCase(filmRepository)

        val actualResult = useCase.loadFullFilmList().map { currentTime to it }.toList()
        val expectedResult = listOf(
            0L to DEFAULT_LIST,
            1000L to emptyList(),
            2000L to DEFAULT_LIST,
        )
        assertThat(actualResult).containsExactlyElementsOf(expectedResult)
        verify(filmRepository).subscribeToFullFilmList()
        verifyNoMoreInteractions(filmRepository)
    }
}
