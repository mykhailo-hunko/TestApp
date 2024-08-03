package com.testapp.presentation.filmlist

import com.testapp.domain.entity.Film

sealed interface FilmListState {

    data object Loading : FilmListState

    data class Loaded(val films: List<Film>) : FilmListState
}