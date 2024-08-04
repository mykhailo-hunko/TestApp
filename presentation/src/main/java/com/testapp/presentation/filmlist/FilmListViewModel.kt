package com.testapp.presentation.filmlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.testapp.domain.usecase.LoadFilmListUseCase
import com.testapp.domain.usecase.SubscribeToFilmListUseCase
import com.testapp.presentation.common.Event
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FilmListViewModel(
    private val subscribeToFilmListUseCase: SubscribeToFilmListUseCase,
    private val loadFilmListUseCase: LoadFilmListUseCase,
) : ViewModel() {

    val state = MutableLiveData<FilmListState>(FilmListState.Loading)

    val events = MutableLiveData<Event<FilmListEvent>>()

    init {
        subscribeToFilmListUseCase
            .loadFullFilmList()
            .map { FilmListState.Loaded(it) }
            .asLiveData().observeForever {
                state.value = it
            }
        onUpdate()
    }

    fun onUpdate() {
        viewModelScope.launch {
            loadFilmListUseCase.loadFullFilmList()
                .onFailure { events.value = Event(FilmListEvent.Error(it)) }
                .onSuccess { events.value = Event(FilmListEvent.StopLoading) }

        }
    }
}