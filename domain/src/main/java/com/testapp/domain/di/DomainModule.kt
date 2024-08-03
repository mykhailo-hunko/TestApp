package com.testapp.domain.di

import com.testapp.domain.usecase.LoadFilmListUseCase
import com.testapp.domain.usecase.SubscribeToFilmListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        LoadFilmListUseCase(filmRepository = get())
    }

    factory {
        SubscribeToFilmListUseCase(filmRepository = get())
    }
}