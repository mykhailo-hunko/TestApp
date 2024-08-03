package com.testapp.presentation.filmlist

sealed interface FilmListEvent {

    data class Error(val message: String?) : FilmListEvent

    data object StopLoading : FilmListEvent
}