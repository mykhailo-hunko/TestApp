package com.testapp.data.di

import androidx.room.Room
import com.testapp.data.api.FilmsApi
import com.testapp.data.database.FilmDatabase
import com.testapp.data.repository.FilmRepositoryImpl
import com.testapp.domain.repository.FilmRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory


val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            FilmDatabase::class.java, "film-database"
        ).build()
    }
    factory<FilmRepository> {
        FilmRepositoryImpl(
            filmApi = get(),
            filmDao = get(),
        )
    }
    factory {
        get<FilmDatabase>().filmDao()
    }
    factory {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
    }
    factory {
        get<Retrofit>().create(FilmsApi::class.java)
    }
}