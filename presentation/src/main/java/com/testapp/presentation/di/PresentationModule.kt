package com.testapp.presentation.di

import com.testapp.presentation.filmlist.FilmListViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory {
        FilmListViewModel(
            subscribeToFilmListUseCase = get(),
            loadFilmListUseCase = get(),
        )
    }
}