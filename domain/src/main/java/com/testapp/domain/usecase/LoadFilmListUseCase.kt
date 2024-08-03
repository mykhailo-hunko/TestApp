package com.testapp.domain.usecase

import com.testapp.domain.repository.FilmRepository

class LoadFilmListUseCase(
    private val filmRepository: FilmRepository,
) {

    suspend fun loadFullFilmList() = filmRepository.loadFilmList()
}