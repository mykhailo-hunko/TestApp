package com.testapp.domain

import com.testapp.domain.repository.FilmRepository
import com.testapp.domain.usecase.LoadFilmListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

class LoadFilmListUseCaseTest {

    @Test
    fun `should return success result`() = runTest {
        val expectedResult = Result.success(Unit)
        val filmRepository: FilmRepository = mock {
            onBlocking { loadFilmList() } doReturn expectedResult
        }
        val useCase = LoadFilmListUseCase(filmRepository)

        val actualResult = useCase.loadFullFilmList()

        assertThat(actualResult).isEqualTo(expectedResult)
        verify(filmRepository).loadFilmList()
        verifyNoMoreInteractions(filmRepository)
    }

    @Test
    fun `should return success result for empty list`() = runTest {
        val expectedResult = Result.success(Unit)
        val filmRepository: FilmRepository = mock {
            onBlocking { loadFilmList() } doReturn expectedResult
        }
        val useCase = LoadFilmListUseCase(filmRepository)

        val actualResult = useCase.loadFullFilmList()

        assertThat(actualResult).isEqualTo(expectedResult)
        verify(filmRepository).loadFilmList()
        verifyNoMoreInteractions(filmRepository)
    }
}
