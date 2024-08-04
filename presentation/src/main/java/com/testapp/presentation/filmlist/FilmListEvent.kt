package com.testapp.presentation.filmlist

sealed interface FilmListEvent {

    data class Error(val error: Throwable) : FilmListEvent

    data object StopLoading : FilmListEvent
}
