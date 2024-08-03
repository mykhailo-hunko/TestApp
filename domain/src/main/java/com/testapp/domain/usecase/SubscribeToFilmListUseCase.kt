package com.testapp.domain.usecase

import com.testapp.domain.repository.FilmRepository

class SubscribeToFilmListUseCase(
    private val filmRepository: FilmRepository,
) {

    fun loadFullFilmList() = filmRepository.subscribeToFullFilmList()
}